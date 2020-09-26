import React from "react";
import MainPage from "./views/MainPage"

class App extends React.Component{
    constructor(props) {
        super(props);
    }


    render() {
        return(
            <div>
                <MainPage/>
            </div>

        )
    }


}

export default App;