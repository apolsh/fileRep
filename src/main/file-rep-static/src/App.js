import React from "react";
import MainPage from "./views/MainPage"
import LoginPage from "./views/LoginPage";

class App extends React.Component{
    constructor(props) {
        super(props);
    }


    render() {
        return(
            <div>
                {/*<LoginPage/>*/}
                <MainPage/>
            </div>

        )
    }


}

export default App;