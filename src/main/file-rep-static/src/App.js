import React from "react";
import MainPage from "./views/MainPage"
import LoginPage from "./views/LoginPage";

class App extends React.Component{
    constructor(props) {
        super(props);
        this.state = {
            auth: false
        }
    }


    render() {
        const {auth} = this.state;
        return(
            <div>
                {!auth ? <LoginPage passedAuth={()=>this.setState({auth:true})}/> :
                    <MainPage/>}

            </div>

        )
    }


}

export default App;