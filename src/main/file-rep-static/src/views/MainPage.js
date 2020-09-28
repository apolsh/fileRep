import React from "react";
import AppBar from "@material-ui/core/AppBar";
import Typography from "@material-ui/core/Typography";
import withStyles from "@material-ui/core/styles/withStyles";
import {fade} from "@material-ui/core";
import InputBase from "@material-ui/core/InputBase";
import SearchIcon from "@material-ui/icons/Search";
import IconButton from "@material-ui/core/IconButton";
import {AccountCircle, AddToPhotos, Build, CreateNewFolder, Delete, Tune, Visibility} from "@material-ui/icons";
import Button from "@material-ui/core/Button";
import Toolbar from "@material-ui/core/Toolbar";
import MenuIcon from '@material-ui/icons/Menu';
import Divider from "@material-ui/core/Divider";
import blue from "@material-ui/core/colors/blue";
import red from "@material-ui/core/colors/red";
import Container from "@material-ui/core/Container";
import Paper from "@material-ui/core/Paper";
import Select from "@material-ui/core/Select";
import MenuItem from "@material-ui/core/MenuItem";
import Tree from "../components/Tree";
import DocumentDialog from "../components/DocumentDialog";
import PublishIcon from '@material-ui/icons/Publish';
import CloudDownloadIcon from '@material-ui/icons/CloudDownload';
import CloudUploadIcon from '@material-ui/icons/CloudUpload';
import FolderDialog from "../components/FolderDialog";
import {getSectionsReq, getSectionTreeReq, createFolder, getFolderByIdReq, updateFolderReq} from "../api/api";
import AddVersionDialog from "../components/AddVersionDialog";

const styles = theme=>({

    root: {
        flexGrow: 1,
    },
    appBar:{
        backgroundColor:'#1976d2'
    },
    title:{
        minWidth:210
    },
    iconButton: {
        marginRight: theme.spacing(0.5),
        marginLeft: theme.spacing(0.5),
    },
    searchFilterButton:{
        marginRight: theme.spacing(0.5),
    },
    search: {
        position: 'relative',
        borderRadius: theme.shape.borderRadius,
        backgroundColor: fade(theme.palette.common.white, 0.15),
        '&:hover': {
            backgroundColor: fade(theme.palette.common.white, 0.25),
        },
        marginRight: theme.spacing(2),
        marginLeft: theme.spacing(4),
        width: '100%'
    },
    searchIcon: {
        padding: theme.spacing(0, 2),
        height: '100%',
        position: 'absolute',
        pointerEvents: 'none',
        display: 'flex',
        alignItems: 'center',
        justifyContent: 'center',
    },
    inputRoot: {
        color: 'inherit',
        width: "100%"
    },
    inputInput: {
        padding: theme.spacing(1, 1, 1, 0),
        // vertical padding + font size from searchIcon
        paddingLeft: `calc(1em + ${theme.spacing(4)}px)`,
        transition: theme.transitions.create('width'),
        width: '100%',
    },
    sectionSelector: {
        paddingLeft: theme.spacing(2),
        width: theme.spacing(40)
    }
});

const renderSections =(sections)=>{
    return sections.map(section=><MenuItem key={section.id} value={section.id}>{section.title}</MenuItem>)
}

class MainPage extends React.Component{
    constructor(props) {
        super(props);
        this.state= {
            addVersionDialogIsOpen: false,
            viewDocumentIsOpen: false,
            folderDialogIsOpen: false,
            sections: [],
            selectedSection: 0,
            sectionTree: null,
            selectedIndex: null,
            selectedType: "folder",
            selectedFolderName: null,
            selectedFolder:null,
            selectedDocument: null,

        }

        this.selectedFolder = null;
        this.selectedDocument = null;

    }

    updateData(){
        this.getSections();
    }

    getSections=()=>{
        getSectionsReq()
            .then(sections=>{
                this.setState({sections: sections, selectedSection: sections[0].id}, this.getSectionTree)
            })
    }

    getSectionTree=()=>{
        const {selectedSection} = this.state;
        getSectionTreeReq(selectedSection).then(result => this.setState({sectionTree: result}));
    }

    componentDidMount() {
        this.updateData();
    }

    onTreeElementSelect = (element) =>{
        const index = element.id === "root" ? element.id : element.id.split("_")[1];
        console.log("index = " + index);
        console.log("type = " + element.type);
        console.log("name = " + element.name);
        this.setState({
            selectedIndex: index,
            selectedType: element.type,
            selectedFolderName: element.name,
        })
        this.selectedElement = element;
    }

    openCreateFolderDialog= ()=>{
        this.setState({
            selectedDocument:null,
            folderDialogIsOpen: true
        })
    }

    openCreateDocumentDialog = () => {
        this.setState({
            selectedFolder:null,
            viewDocumentIsOpen: true
        })
    }

    openAddVersionDialog = () => {
        this.setState({
            addVersionDialogIsOpen: true
        })
    }

    onSaveDocument = (document)=>{
        console.log(document)
    }

    onSaveVersion = (version) => {
        const {selectedIndex} = this.state;

        version.id = 1;
        version.docId = selectedIndex;
        console.log(version)
    }

    onViewClick = () => {

        const {selectedType, selectedIndex} = this.state;

        if(selectedType === "folder"){
            getFolderByIdReq(selectedIndex).then(folder=> {
                if(folder.parentId){
                    getFolderByIdReq(folder.parentId)
                        .then(parent=>{
                            this.setState({
                                folderDialogIsOpen: true,
                                selectedFolder: folder,
                                selectedFolderName: parent.title
                            })
                        })
                }else{
                    this.setState({
                        folderDialogIsOpen: true,
                        selectedFolder: folder,
                        selectedFolderName: "Корневая директория"
                    })
                }
            });
        }
    }

    onSaveFolder = (folder, createNew) => {
        const {selectedIndex, selectedSection} = this.state;

        if(createNew){
            folder.parentId = selectedIndex === "root" ? null : selectedIndex;
            folder.sectionId = selectedSection;


            createFolder(folder)
                .then(()=> {
                    this.getSectionTree()
                    this.setState({
                        folderDialogIsOpen: false
                    })
                })
        }else{
            updateFolderReq(folder)
                .then(()=> {
                    this.getSectionTree();
                    this.setState({
                        folderDialogIsOpen: false
                    });
                })
        }


    }

    renderSelectedElementMenu(){
        const {selectedIndex, selectedType} = this.state;
        const {classes} = this.props;

        const renderedList = [];

        if(selectedIndex === null){
            return renderedList;
        }

        if(selectedType === "document"){
            renderedList.push(<IconButton
                key={"downloadBtn"}
                className={classes.iconButton}
                edge="end"
                aria-label="account of current user"
                aria-haspopup="true"
                color="primary"
            >
                <CloudDownloadIcon  fontSize="large"/>
            </IconButton>)
            renderedList.push(<IconButton
                key={"uploadBtn"}
                className={classes.iconButton}
                edge="end"
                aria-label="account of current user"
                aria-haspopup="true"
                color="primary"
                onClick={this.openAddVersionDialog}
            >
                <PublishIcon fontSize="large" style={{color: '#388e3c'}}/>
            </IconButton>)
        }else{
            renderedList.push(<IconButton
                key={"newFolderBtn"}
                className={classes.iconButton}
                edge="end"
                aria-label="account of current user"
                aria-haspopup="true"
                color="inherit"
                onClick={this.openCreateFolderDialog}
            >
                <CreateNewFolder fontSize="large" />
            </IconButton>)
            renderedList.push(<IconButton
                key={"newDocumentBtn"}
                className={classes.iconButton}
                edge="end"
                aria-label="account of current user"
                aria-haspopup="true"
                color="inherit"
                onClick={this.openCreateDocumentDialog}
            >
                <AddToPhotos fontSize="large" />
            </IconButton>)
        }

        if(selectedIndex !== "root"){
            renderedList.unshift(<IconButton
                key={"viewBtn"}
                className={classes.iconButton}
                edge="end"
                aria-label="account of current user"
                aria-haspopup="true"
                color="inherit"
                fontSize='large'
                onClick={this.onViewClick}
            >
                <Visibility fontSize="large"/>
            </IconButton>)
            renderedList.push(<IconButton
                key={"deleteBtn"}
                className={classes.iconButton}
                edge="end"
                aria-label="account of current user"
                aria-haspopup="true"
                color="secondary"
            >
                <Delete fontSize="large" />
            </IconButton>)
        }

        return renderedList;
    }


    render (){
        const {viewDocumentIsOpen, folderDialogIsOpen, sections, selectedSection, sectionTree, selectedFolderName, selectedFolder, selectedDocument, addVersionDialogIsOpen} = this.state;
        const {classes} = this.props;

        console.log(selectedSection);

        return <div className={classes.root}>
            <AddVersionDialog
                isOpen={addVersionDialogIsOpen}
                onClose={()=>this.setState({addVersionDialogIsOpen: false})}
                documentTitle={selectedFolderName}
                onSave={this.onSaveVersion}
            />
            <DocumentDialog
                isOpen={viewDocumentIsOpen}
                onClose={()=>this.setState({viewDocumentIsOpen: false})}
                parentFolder={selectedFolderName}
                documentObject={selectedDocument}
                onSave={this.onSaveDocument}
            />
            <FolderDialog
                isOpen={folderDialogIsOpen}
                onClose={()=>this.setState({folderDialogIsOpen: false})}
                folderObject={selectedFolder}
                parentFolder={selectedFolderName}
                onSave={this.onSaveFolder}
            />
            <AppBar position="static" className={classes.appBar}>
                <Toolbar>

                    <Typography edge="start" variant="h6" className={classes.title}>
                        Файловое хранилище
                    </Typography>

                    <div className={classes.search}>
                        <div className={classes.searchIcon}>
                            <SearchIcon />
                        </div>
                        <InputBase
                            placeholder="Search…"
                            classes={{
                                root: classes.inputRoot,
                                input: classes.inputInput,
                            }}
                            inputProps={{ 'aria-label': 'search' }}
                        />
                    </div>
                    <IconButton
                        className={classes.searchFilterButton}
                        edge="end"
                        aria-label="account of current user"
                        aria-haspopup="true"
                        color="inherit"
                    >
                        <Tune />
                    </IconButton>
                    <Divider light={true} orientation="vertical" flexItem />

                    <IconButton
                        className={classes.iconButton}
                        edge="end"
                        aria-label="account of current user"
                        aria-haspopup="true"
                        color="inherit"
                    >
                        <Build />
                    </IconButton>
                    <IconButton
                        className={classes.iconButton}
                        edge="end"
                        aria-label="account of current user"
                        aria-haspopup="true"
                        color="inherit"
                    >
                        <AccountCircle />
                    </IconButton>

                </Toolbar>

            </AppBar>

            <br/>

            <Container maxWidth="md">
                <Typography variant="body1" style={{display: 'inline-block'}} >
                    Выберите раздел:
                </Typography>
                <Select
                    className={classes.sectionSelector}
                    value={selectedSection}
                    onChange={event=>this.setState({selectedSection: event.target.value})}
                    displayEmpty
                    inputProps={{ 'aria-label': 'Without label' }}
                >
                    {renderSections(sections)}
                </Select>


                {this.renderSelectedElementMenu()}



                <br/>

                <Tree tree={sectionTree} onSelect={this.onTreeElementSelect}/>

            </Container>
        </div>

    }
}

export default withStyles(styles)(MainPage);