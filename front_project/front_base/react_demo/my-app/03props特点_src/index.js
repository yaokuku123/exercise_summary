import React from 'react';
import ReactDOM from 'react-dom';

class App extends React.Component {
    constructor(props) {
        super(props)
        console.log(this.props) // 要在创建函数中使用props，必须把props加载到super里
    }
    render() {
        console.log('render：', this.props)
        console.log(this.props.fn());
        return (
            <div>
                <h1>name: {this.props.name}, age: {this.props.age}</h1>
                {this.props.tag}
            </div>
        )
    }
}

ReactDOM.render(<App
        name="yorick" // 传字符串
        age={18} // 传数值
        tag={<div>hello tag</div>} // 传JSX
        fn={() => "hello fn"}/>, // 传函数
    document.getElementById('root'));