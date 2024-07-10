import React from 'react'
import ReactDOM from 'react-dom'

import './index.css'
/* 
  Context
*/

// 创建context得到两个组件
const {Consumer, Provider} = React.createContext()

class App extends React.Component {
    render() {
        return (
            <Provider value="yorick">
                <div className="app">
                    <Node/>
                </div>
            </Provider>
        )
    }
}

const Node = props => {
    return (
        <div className="node">
            <SubNode/>
        </div>
    )
}

const SubNode = props => {
    return (
        <div className="subnode">
            <Child/>
        </div>
    )
}

const Child = props => {
    return (
        <div className="child">
            <Consumer>
                {data => <span>name: {data} </span>}
            </Consumer>
        </div>
    )
}

ReactDOM.render(<App/>, document.getElementById('root'))
