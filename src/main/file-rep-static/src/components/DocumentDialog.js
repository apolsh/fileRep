import React from 'react';
import Button from '@material-ui/core/Button';
import TextField from '@material-ui/core/TextField';
import Dialog from '@material-ui/core/Dialog';
import DialogActions from '@material-ui/core/DialogActions';
import DialogContent from '@material-ui/core/DialogContent';
import DialogContentText from '@material-ui/core/DialogContentText';
import DialogTitle from '@material-ui/core/DialogTitle';
import Tabs from "@material-ui/core/Tabs";
import Paper from "@material-ui/core/Paper";
import Tab from "@material-ui/core/Tab";
import AppBar from "@material-ui/core/AppBar";
import Box from "@material-ui/core/Box";
import Typography from "@material-ui/core/Typography";
import Container from "@material-ui/core/Container";
import Chip from "@material-ui/core/Chip";
import makeStyles from "@material-ui/core/styles/makeStyles";
import Toolbar from "@material-ui/core/Toolbar";
import CloudDownloadIcon from '@material-ui/icons/CloudDownload';
import IconButton from "@material-ui/core/IconButton";
import {Add, AddBox, Build} from "@material-ui/icons";
import Accordion from "@material-ui/core/Accordion";
import AccordionSummary from "@material-ui/core/AccordionSummary";
import AccordionDetails from "@material-ui/core/AccordionDetails";
import ExpandMoreIcon from '@material-ui/icons/ExpandMore';
import {getDateTime} from "../utils/dateUtils";
import Grid from "@material-ui/core/Grid";
import PublishIcon from '@material-ui/icons/Publish';
import StarIcon from '@material-ui/icons/Star';
import DoneIcon from '@material-ui/icons/Done';
import AddCircleOutlineOutlinedIcon from '@material-ui/icons/AddCircleOutlineOutlined';
import CheckIcon from '@material-ui/icons/Check';

function TabPanel(props) {
    const { children, value, index, ...other } = props;

    return (
        <div
            role="tabpanel"
            hidden={value !== index}
            id={`simple-tabpanel-${index}`}
            aria-labelledby={`simple-tab-${index}`}
            {...other}
        >
            {value === index && (
                <Box p={3}>
                    {children}
                </Box>
            )}
        </div>
    );
}

const useStyles = makeStyles((theme) => ({
    tags: {
        marginTop: 5,
        display: 'flex',
        justifyContent: 'center',
        flexWrap: 'wrap',
        '& > *': {
            margin: theme.spacing(0.5),
        },
    },
    dialogElements:{
        marginTop:10,
        marginBottom:10
    },
    versionsRoot: {
        width: '100%',
    },
}));

export default function DocumentDialog({isOpen, onClose, parentFolder, documentObject, onSave}) {

    const classes = useStyles();

    const [tabIndex, setTabindex] = React.useState(0);
    const [title, setTitle] = React.useState("");
    const [note, setNote] = React.useState("");
    const [tagInput, setTagInput] = React.useState("");
    const [tagInputIsOpen, setTagInputIsOpen] = React.useState(false);

    const handleTabChange = (event, newValue) => {
        setTabindex(newValue);
    };

    return (
            <Dialog open={isOpen} onClose={console.log} aria-labelledby="form-dialog-title">
                <AppBar position="static" color="default">
                    <Tabs
                        value={tabIndex}
                        indicatorColor="primary"
                        textColor="primary"
                        variant="fullWidth"
                        onChange={handleTabChange}
                        aria-label="disabled tabs example"
                    >
                        <Tab label="Информация о документе:" />
                        <Tab label="Версии документа:" />
                    </Tabs>
                </AppBar>
                <DialogContent>
                    <TabPanel value={tabIndex} index={0}>
                        <TextField
                            className={classes.dialogElements}
                            required id="standard-required"
                            label="Наименование"
                            defaultValue="Спецификация 123"
                            fullWidth
                            value={title}
                            onChange={event=>setTitle(event.target.value)}
                        /><br/>
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
                        <TextField
                            disabled={true}
                            className={classes.dialogElements}
                            label="Родительская папка"
                            value={parentFolder}
                            fullWidth
                        /><br/>

                        <div className={classes.dialogElements}>
                            <Typography variant="body1" style={{display: 'inline-block'}}>
                                Тэги:
                            </Typography>
                            {tagInputIsOpen ?
                                <div>
                                    <TextField
                                        style={{display: 'inline-block'}}
                                        className={classes.dialogElements}
                                        required id="standard-required"
                                        label="Добавить тэг:"
                                        value={tagInput}
                                        onChange={event=>setTagInput(event.target.value)}
                                    />
                                    <IconButton
                                        className={classes.iconButton}
                                        edge="end"
                                        aria-label="account of current user"
                                        aria-haspopup="true"
                                        color="inherit"
                                        onClick={()=>setTagInputIsOpen(false)}
                                    >
                                        <CheckIcon color='primary'/>
                                    </IconButton>
                                </div>
                                :
                                <IconButton
                                    style={{float:'right'}}
                                    className={classes.iconButton}
                                    edge="end"
                                    aria-label="account of current user"
                                    aria-haspopup="true"
                                    color="inherit"
                                    onClick={()=>setTagInputIsOpen(true)}
                                >
                                    <AddCircleOutlineOutlinedIcon color='primary'/>
                                </IconButton>
                            }

                            <div className={classes.tags}>
                                <Chip label="Спецификация" onDelete={console.log} color="default" />
                                <Chip label="Релиз 3.18.50" onDelete={console.log} color="default" />
                                <Chip label="Хорошие документы" onDelete={console.log} color="default" />
                            </div>
                        </div>

                        <div style={{width:'100%', textAlign:'center'}}>
                            <Button
                                size={'small'}
                                variant="contained"
                                color="primary"
                                style={{marginTop:20}}
                                startIcon={<CloudDownloadIcon />}
                            >
                                Скачать актуальную версию
                            </Button>
                            <Button
                                size={'small'}
                                variant="contained"
                                color="primary"
                                style={{marginLeft:10, marginTop:20, backgroundColor:'#388e3c', color: 'white'}}
                                startIcon={<PublishIcon />}
                            >
                                Загрузить новую версию
                            </Button>
                        </div>



                    </TabPanel>
                    <TabPanel value={tabIndex} index={1}>
                        <div className={classes.versionsRoot}>
                            <Accordion>
                                <AccordionSummary
                                    expandIcon={<ExpandMoreIcon />}
                                    aria-controls="panel1a-content"
                                    id="panel1a-header"
                                >
                                    <div style={{width:'100%', display: 'block'}}>
                                        <Typography style={{display: 'inline-block'}}>Версия от {getDateTime(new Date())}</Typography>
                                        <Chip
                                            style={{float:'right', backgroundColor:'purple', color: 'white'}}
                                            label="Актуальная"
                                            clickable
                                            color="primary"
                                            onDelete={console.log}
                                            deleteIcon={<DoneIcon />}
                                        />
                                    </div>

                                </AccordionSummary>
                                <AccordionDetails style={{display:'block'}}>
                                    <hr/>
                                    <div>
                                        <Typography variant="body1"  style={{display: 'inline-block'}} >
                                            <b>Описание:</b>
                                        </Typography>
                                        <Typography variant="body2">
                                            Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse malesuada lacus ex,
                                            sit amet blandit leo lobortis eget.
                                        </Typography>
                                    </div>
                                    <br/>
                                    <div>
                                        <Typography variant="body1"  style={{display: 'inline-block'}} >
                                            <b>Загрузил:</b>
                                        </Typography>
                                        <Typography variant="body1">
                                            Иванов Иван Иванович (ivanov@mail.ru)
                                        </Typography>
                                    </div>
                                    <br/>
                                    <div>
                                        <Typography variant="body1"  style={{display: 'inline-block'}} >
                                            <b>Дата загрузки:</b>
                                        </Typography>
                                        <Typography variant="body1">
                                            {getDateTime(new Date())}
                                        </Typography>
                                    </div>
                                    <br/>
                                    <div>
                                        <Typography variant="body1"  style={{display: 'inline-block'}} >
                                            <b>Тип файла:</b>
                                        </Typography>
                                        <Typography variant="body1">
                                            application/xml
                                        </Typography>
                                    </div>
                                    <br/>
                                    <div>
                                        <Typography variant="body1"  style={{display: 'inline-block'}} >
                                            <b>Размер файла:</b>
                                        </Typography>
                                        <Typography variant="body1">
                                            123кб
                                        </Typography>
                                    </div>
                                    <hr/>
                                    <div>
                                        <Grid container justify="center" spacing={4}>
                                            <Grid key={1} item>
                                                <Button variant="contained" color="secondary">
                                                    Удалить
                                                </Button>
                                            </Grid>
                                            <Grid key={2} item>
                                                <Button variant="contained" style={{backgroundColor:'purple', color: 'white'}}>
                                                    Актуальная версия
                                                </Button>
                                            </Grid>
                                            <Grid key={3} item>
                                                <Button variant="contained" color="primary">
                                                    Скачать
                                                </Button>
                                            </Grid>
                                        </Grid>


                                    </div>

                                </AccordionDetails>
                            </Accordion>
                            <Accordion>
                                <AccordionSummary
                                    expandIcon={<ExpandMoreIcon />}
                                    aria-controls="panel1a-content"
                                    id="panel1a-header"
                                >
                                    <Typography >Версия от {getDateTime(new Date())}</Typography>
                                </AccordionSummary>
                                <AccordionDetails style={{display:'block'}}>
                                    <hr/>
                                    <div>
                                        <Typography variant="body1"  style={{display: 'inline-block'}} >
                                            <b>Описание:</b>
                                        </Typography>
                                        <Typography variant="body2">
                                            Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse malesuada lacus ex,
                                            sit amet blandit leo lobortis eget.
                                        </Typography>
                                    </div>
                                    <br/>
                                    <div>
                                        <Typography variant="body1"  style={{display: 'inline-block'}} >
                                            <b>Загрузил:</b>
                                        </Typography>
                                        <Typography variant="body1">
                                            Иванов Иван Иванович (ivanov@mail.ru)
                                        </Typography>
                                    </div>
                                    <br/>
                                    <div>
                                        <Typography variant="body1"  style={{display: 'inline-block'}} >
                                            <b>Дата загрузки:</b>
                                        </Typography>
                                        <Typography variant="body1">
                                            {getDateTime(new Date())}
                                        </Typography>
                                    </div>
                                    <br/>
                                    <div>
                                        <Typography variant="body1"  style={{display: 'inline-block'}} >
                                            <b>Тип файла:</b>
                                        </Typography>
                                        <Typography variant="body1">
                                            application/xml
                                        </Typography>
                                    </div>
                                    <br/>
                                    <div>
                                        <Typography variant="body1"  style={{display: 'inline-block'}} >
                                            <b>Размер файла:</b>
                                        </Typography>
                                        <Typography variant="body1">
                                            123кб
                                        </Typography>
                                    </div>
                                    <hr/>
                                    <div>
                                        <Grid container justify="center" spacing={4}>
                                            <Grid key={1} item>
                                                <Button variant="contained" color="secondary">
                                                    Удалить
                                                </Button>
                                            </Grid>
                                            <Grid key={2} item>
                                                <Button variant="contained" style={{backgroundColor:'purple', color: 'white'}}>
                                                    Актуальная версия
                                                </Button>
                                            </Grid>
                                            <Grid key={3} item>
                                                <Button variant="contained" color="primary">
                                                    Скачать
                                                </Button>
                                            </Grid>
                                        </Grid>


                                    </div>

                                </AccordionDetails>
                            </Accordion>
                        </div>

                    </TabPanel>

                </DialogContent>
                <DialogActions>

                    <Button onClick={onClose} color="secondary">
                        Отмена
                    </Button>
                    <Button onClick={console.log} color="primary">
                        Сохранить
                    </Button>
                </DialogActions>
            </Dialog>
    );
}