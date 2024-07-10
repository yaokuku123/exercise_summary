import React from 'react'
import ReactDOM from 'react-dom'

import './index.css'
/* 
  props
*/

// 父组件
class Parent extends React.Component {
    state = {
        parentMsg: ''
    }

    // 提供回调函数，用来接收数据
    getMsg = data => {
        console.log(data)
        console.log('parent this: ',this)
        this.setState({
            parentMsg: data
        })
    }

    render() {
        return (
            <div className="parent">
                父组件：{this.state.parentMsg}
                <Child getMsg={this.getMsg}/>
            </div>
        )
    }
}

// 子组件
class Child extends React.Component {
    state = {
        msg: "刷抖音"
    }

    handleClick = () => {
        console.log('child this: ',this)
        // 子组件调用父组件中传递过来的回调函数
        this.props.getMsg(this.state.msg)
    }

    render() {
        return (
            <div className="child">
                子组件：{' '}
                <button onClick={this.handleClick}>点我，给父组件传递数据</button>
            </div>
        )
    }
}

ReactDOM.render(<Parent/>, document.getElementById('root'))
