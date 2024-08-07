import React from 'react'
import ReactDOM from 'react-dom'

/* 
  props校验
*/

import PropTypes from 'prop-types'

const App = props => {
    console.log(props)
    return (
        <div>
            <h1>props校验：</h1>
            <h2>props默认值：{props.pageSize}</h2>
        </div>
    )
}

// 添加props校验
// 属性 a 的类型：      数值（number）
// 属性 fn 的类型：     函数（func）并且为必填项
// 属性 tag 的类型：    React元素（element）
// 属性 filter 的类型： 对象（{area: '上海', price: 1999}）
App.propTypes = {
    a: PropTypes.number,
    fn: PropTypes.func.isRequired,
    tag: PropTypes.element,
    filter: PropTypes.shape({
        area: PropTypes.string,
        price: PropTypes.number
    })
}

// props添加默认值
App.defaultProps = {
    pageSize: 10
}


ReactDOM.render(<App
    a={20}
    fn={() => {
        console.log("hello fn")
    }}
    tag={<div>hello tag</div>}
    filter={{area: "bj", price: 123}}/>, document.getElementById('root'))
