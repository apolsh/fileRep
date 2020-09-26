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

export default function FolderDialog({isOpen}) {

    const classes = useStyles();

    return (
        <div>

            <Dialog open={isOpen} onClose={console.log} aria-labelledby="form-dialog-title" maxWidth="sm" fullWidth={true}>
                <DialogTitle id="form-dialog-title">Создание новой папки</DialogTitle>
                <DialogContent>
                    <TextField className={classes.dialogElements} required id="standard-required" label="Наименование" defaultValue="Спецификации важные" fullWidth/><br/>
                    <TextField
                        className={classes.dialogElements}
                        id="standard-multiline-flexible"
                        label="Описание"
                        multiline
                        rowsMax={4}
                        value={"Это документ очень важный, прям супер-пупер, ляляляя. Вот так вот. Это документ очень важный, прям супер-пупер, ляляляя. Вот так вот."}
                        onChange={console.log}
                        fullWidth
                    />
                    <br/>
                    <TextField disabled={true} className={classes.dialogElements} label="Родительская папка" defaultValue="Спецификации" fullWidth/><br/>
                </DialogContent>
                <DialogActions>
                    <Button onClick={console.log} color="secondary">
                        Отмена
                    </Button>
                    <Button onClick={console.log} color="primary">
                        Сохранить
                    </Button>
                </DialogActions>
            </Dialog>
        </div>
    );
}