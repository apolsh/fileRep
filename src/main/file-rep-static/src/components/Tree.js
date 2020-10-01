import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import TreeView from '@material-ui/lab/TreeView';
import ExpandMoreIcon from '@material-ui/icons/ExpandMore';
import ChevronRightIcon from '@material-ui/icons/ChevronRight';
import TreeItem from '@material-ui/lab/TreeItem';
import {Delete, Folder, InsertDriveFile} from "@material-ui/icons";
import Typography from "@material-ui/core/Typography";

const useStyles = makeStyles((theme)=>({
    root: {
        height: '100%',
        flexGrow: 1,
        maxWidth: '100%',
    },
    labelRoot: {
        display: 'flex',
        alignItems: 'center',
        padding: theme.spacing(0.5, 0),

    },
    labelIcon: {
        marginRight: theme.spacing(1),
        color: 'rgba(0, 0, 0, 0.54)'
    },
    labelText: {
        fontWeight: 'inherit',
        flexGrow: 1,
    },


}));

export default function Tree({tree, onSelect}) {
    const classes = useStyles();
    const [expanded, setExpanded] = React.useState([]);
    const [selected, setSelected] = React.useState([]);

    const handleSelect = (event, nodeId) => {
        if(selected === nodeId){
            const expandedCopy = [...expanded];
            const index = expanded.indexOf(nodeId)
            if(index !== -1){
                expandedCopy.splice(index, 1);
            }else{
                expandedCopy.push(nodeId);
            }
            setExpanded(expandedCopy);
        }else{
            setSelected(nodeId);
        }
    };

    const renderTreeElement = element =>{
        return (
            <TreeItem key={element.id}
                      nodeId={element.id}
                      label={<div className={classes.labelRoot}>
                                {element.type === "folder" ? <Folder color="inherit" className={classes.labelIcon} />
                                : <InsertDriveFile color="inherit" className={classes.labelIcon} />}
                                <Typography variant="body1" className={classes.labelText}>
                                    {element.name}
                                </Typography>
                            </div>}
                      onClick={()=> {
                          if(element.id !== selected) onSelect(element);
                      }}
            >
                {element.children ? element.children.map(treeElement=>renderTreeElement(treeElement)):null}
            </TreeItem>
        );

    }

    const renderRunner = (tree) =>{
        if(Array.isArray(tree)){
            return tree.map(element=>renderTreeElement(element));
        }else{
            return renderTreeElement(tree);
        }
    }


    return (
        <TreeView
            className={classes.root}
            defaultCollapseIcon={<ExpandMoreIcon />}
            defaultExpandIcon={<ChevronRightIcon />}
            expanded={expanded}
            selected={selected}
            onNodeSelect={handleSelect}
        >
            {tree ? renderRunner(tree) : null}
            {/*<TreeItem nodeId="1" label={*/}
            {/*    <div className={classes.labelRoot}>*/}
            {/*        <Folder color="inherit" className={classes.labelIcon} />*/}
            {/*        <Typography variant="body1" className={classes.labelText}>*/}
            {/*            Application*/}
            {/*        </Typography>*/}
            {/*    </div>*/}

            {/*}>*/}
            {/*    <TreeItem nodeId="2" label={*/}
            {/*        <div className={classes.labelRoot}>*/}
            {/*            <InsertDriveFile color="inherit" className={classes.labelIcon} />*/}
            {/*            <Typography variant="body1" className={classes.labelText}>*/}
            {/*                Firefox*/}
            {/*            </Typography>*/}
            {/*        </div>*/}

            {/*    }/>*/}
            {/*    <TreeItem nodeId="3" label={*/}
            {/*        <div className={classes.labelRoot}>*/}
            {/*            <InsertDriveFile color="inherit" className={classes.labelIcon} />*/}
            {/*            <Typography variant="body1" className={classes.labelText}>*/}
            {/*                Chrome*/}
            {/*            </Typography>*/}
            {/*        </div>*/}

            {/*    }/>*/}
            {/*    <TreeItem nodeId="4" label={*/}
            {/*        <div className={classes.labelRoot}>*/}
            {/*            <InsertDriveFile color="inherit" className={classes.labelIcon} />*/}
            {/*            <Typography variant="body1" className={classes.labelText}>*/}
            {/*                Webstorm*/}
            {/*            </Typography>*/}
            {/*        </div>*/}

            {/*    }/>*/}
            {/*</TreeItem>*/}
            {/*<TreeItem nodeId="5" label="Documents">*/}
            {/*    <TreeItem nodeId="6" label="Material-UI">*/}
            {/*        <TreeItem nodeId="7" label="src">*/}
            {/*            <TreeItem nodeId="8" label="index.js" />*/}
            {/*            <TreeItem nodeId="9" label="tree-view.js" />*/}
            {/*        </TreeItem>*/}
            {/*    </TreeItem>*/}
            {/*</TreeItem>*/}
        </TreeView>
    );
}