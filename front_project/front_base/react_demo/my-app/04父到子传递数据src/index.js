import React from 'react'
import ReactDOM from 'react-dom'

import './index.css'
/* 
  props
*/

// 父组件
class Parent extends React.Component {
  state = {
      msg: "hello children"
  }

  render() {
    return (
      <div className="parent">
        父组件：
        <Child msg={this.state.msg}/>
      </div>
    )
  }
}

// 子组件
const Child = (props) => {
  return (
    <div className="child">
      <p>子组件，接收到父组件的数据：{props.msg}</p>
    </div>
  )
}

ReactDOM.render(<Parent />, document.getElementById('root'))
