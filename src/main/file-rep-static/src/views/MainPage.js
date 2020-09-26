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
import DocumentViewDialog from "../components/DocumentViewDialog";
import PublishIcon from '@material-ui/icons/Publish';
import CloudDownloadIcon from '@material-ui/icons/CloudDownload';
import CloudUploadIcon from '@material-ui/icons/CloudUpload';
import FolderDialog from "../components/FolderDialog";

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

class MainPage extends React.Component{
    constructor(props) {
        super(props);
        this.state={
            viewDocumentIsOpen: false,
            folderDialogIsOpen: true,

        }
    }



    render (){
        const {viewDocumentIsOpen, folderDialogIsOpen} = this.state;
        const {classes} = this.props;

        return <div className={classes.root}>
            <DocumentViewDialog isOpen={viewDocumentIsOpen}/>
            <FolderDialog isOpen={folderDialogIsOpen}/>
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
                    value={10}
                    onChange={console.log}
                    displayEmpty
                    inputProps={{ 'aria-label': 'Without label' }}
                >
                    <MenuItem value="">
                        <em>None</em>
                    </MenuItem>
                    <MenuItem value={10}>Ten</MenuItem>
                    <MenuItem value={20}>Twenty</MenuItem>
                    <MenuItem value={30}>Thirty</MenuItem>
                    <MenuItem value={35}>Ten</MenuItem>
                    <MenuItem value={20}>Twenty</MenuItem>
                    <MenuItem value={30}>Thirty</MenuItem>
                    <MenuItem value={36}>Ten</MenuItem>
                    <MenuItem value={20}>Twenty</MenuItem>
                    <MenuItem value={30}>Thirty</MenuItem>
                </Select>
                <IconButton
                    className={classes.iconButton}
                    edge="end"
                    aria-label="account of current user"
                    aria-haspopup="true"
                    color="inherit"
                    fontSize='large'
                >
                    <Visibility fontSize="large"/>
                </IconButton>
                <IconButton
                    className={classes.iconButton}
                    edge="end"
                    aria-label="account of current user"
                    aria-haspopup="true"
                    color="primary"
                >
                    <CloudDownloadIcon  fontSize="large"/>
                </IconButton>
                <IconButton

                    className={classes.iconButton}
                    edge="end"
                    aria-label="account of current user"
                    aria-haspopup="true"
                    color="primary"
                >
                    <PublishIcon fontSize="large" style={{color: '#388e3c'}}/>
                </IconButton>
                <IconButton
                    className={classes.iconButton}
                    edge="end"
                    aria-label="account of current user"
                    aria-haspopup="true"
                    color="inherit"
                >
                    <CreateNewFolder fontSize="large" />
                </IconButton>
                <IconButton
                    className={classes.iconButton}
                    edge="end"
                    aria-label="account of current user"
                    aria-haspopup="true"
                    color="inherit"
                >
                    <AddToPhotos fontSize="large" />
                </IconButton>
                <IconButton
                    className={classes.iconButton}
                    edge="end"
                    aria-label="account of current user"
                    aria-haspopup="true"
                    color="secondary"
                >
                    <Delete fontSize="large" />
                </IconButton>

                <br/>

                <Tree/>

            </Container>
        </div>

    }
}

export default withStyles(styles)(MainPage);