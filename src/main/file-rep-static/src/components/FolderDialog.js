import React from 'react';
import Button from '@material-ui/core/Button';
import TextField from '@material-ui/core/TextField';
import Dialog from '@material-ui/core/Dialog';
import DialogActions from '@material-ui/core/DialogActions';
import DialogContent from '@material-ui/core/DialogContent';
import DialogContentText from '@material-ui/core/DialogContentText';
import DialogTitle from '@material-ui/core/DialogTitle';
import makeStyles from "@material-ui/core/styles/makeStyles";


const useStyles = makeStyles((theme) => ({
    dialogElements:{
        marginTop:10,
        marginBottom:10
    }
}));

export default function FolderDialog({isOpen, onSave, onClose, folderObject, selectedFolderName}) {

    const [title, setTitle] = React.useState("");
    const [note, setNote] = React.useState("");

    const classes = useStyles();


    const prepareSave = ()=>{
        if(folderObject === null){
            onSave({
                id:0,
                title: title,
                note: note,
            })
        }else{
            folderObject.title = title;
            folderObject.note = note;
            onSave(folderObject)
        }
    }



    return (
        <div>

            <Dialog open={isOpen} onClose={console.log} aria-labelledby="form-dialog-title" maxWidth="sm" fullWidth={true}>
                <DialogTitle id="form-dialog-title">Создание новой папки</DialogTitle>
                <DialogContent>
                    <TextField
                        className={classes.dialogElements}
                        required
                        id="standard-required"
                        label="Наименование"
                        defaultValue={title}
                        fullWidth
                        onChange={event=>setTitle(event.target.value)}
                    />
                        <br/>
                    <TextField
                        className={classes.dialogElements}
                        id="standard-multiline-flexible"
                        label="Описание"
                        multiline
                        rowsMax={4}
                        value={note}
                        onChange={event=>setNote(event.target.value)}
                        fullWidth
                    />
                    <br/>
                    <TextField disabled={true} className={classes.dialogElements} label="Родительская папка" defaultValue={selectedFolderName} fullWidth/><br/>
                </DialogContent>
                <DialogActions>
                    <Button onClick={onClose} color="secondary">
                        Отмена
                    </Button>
                    <Button onClick={prepareSave} color="primary">
                        Сохранить
                    </Button>
                </DialogActions>
            </Dialog>
        </div>
    );
}