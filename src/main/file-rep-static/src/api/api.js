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

export const getDocumentReq = async documentId => {
    let response = await server.get(`/documents/${documentId}`)
    return response.data;
}

export const uploadNewVersionReq = async (docId, version) => {
    console.log(version);
    let formData = new FormData();
    Object.keys(version).forEach(key=>{
        formData.append(key, version[key]);
    })
    console.log(formData);
    let response = await server.post(`documents/${docId}/version`, formData);
    return response.data;
}

export const downloadVersionLink = async (versionId) => {
    return `http://127.0.0.1:8080/api/documents/version/${versionId}/content`
}