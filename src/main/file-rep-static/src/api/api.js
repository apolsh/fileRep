import axios from "axios";

const server = axios.create({
    baseURL: 'http://127.0.0.1:8080/api'
})

export const getSectionsReq = async () =>{
    let response = await server.get("/sections");
    return response.data;
}

export const getSectionTreeReq = async (index) =>{
    let response = await server.get(`/sections/${index}/tree`);
    return response.data;
}

export const createFolder = async folder =>{
    let response = await server.post(`/folders`, folder);
    return response.data;
}

export const getFolderByIdReq = async id => {
    let response = await server.get(`/folders/${id}`)
    return response.data;
}

export const updateFolderReq = async folder => {
    let response = await server.put("/folders", folder)
    return response.data;
}