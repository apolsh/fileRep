import dateformat from "dateformat";

export const getDateTime = (date) => {
    return dateformat(date, "dd.mm.yyyy HH:MM:ss");


}