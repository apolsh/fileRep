import React, {useEffect} from 'react';
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

export default function AddVersionDialog({isOpen, onSave, onClose, documentTitle}) {


    const [title, setTitle] = React.useState("");
    const [note, setNote] = React.useState("");
    const [file, setFile] = React.useState({})

    const classes = useStyles();

    const prepareSave = () => {
        let extension = file.name.split('.').length > 1 ? file.name.split('.').pop() : '';
        onSave({
            title: title,
            note: note,
            uploadDate: new Date(),
            mimeType: file.type,
            size: file.size,
            extension: extension,
            file: file
        }, file)

    }


    return (
        <div>

            <Dialog open={isOpen} onClose={onClose} aria-labelledby="form-dialog-title" maxWidth="sm" fullWidth={true}>
                <DialogTitle id="form-dialog-title">Добавление новой версии</DialogTitle>
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
                    <TextField disabled={true} className={classes.dialogElements} label="Документ" defaultValue={documentTitle} fullWidth/><br/>
                    <TextField
                        name="upload-photo"
                        type="file"
                        onChange={event=>setFile(event.target.files[0])}
                    />
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