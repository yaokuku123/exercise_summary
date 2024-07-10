import React from 'react';
import ReactDOM from 'react-dom';

// 函数式声明组件
// const Hello = function () {
//     return (
//         <div>Hello world</div>
//     )
// }

// 对象式声明组件
class Hello extends React.Component {
    // 定义state属性
    state = {
        count: 0,
        txt: '',
        content: '',
        city: 'bj',
        isChecked: false
    }

    // 使用箭头函数的方式处理点击事件，使得this指向当前类，可以调用其中的setState方法
    handleClick = e => {
        console.log('button clicked');
        this.setState({count: this.state.count + 1}); // 修改count字段的值
        e.preventDefault(); // 事件对象，阻止标签的默认行为
    }

    // 处理受控组件
    handleForm = e => {
        const target = e.target
        // 获取标签类型
        const value = target.type === 'checkbox' ? target.checked : target.value;
        // 获取标签名称
        const name = target.name;
        // 设置状态
        this.setState({
            [name]: value
        })
        console.log(this.state[name])
    }

    // 需要重写render方法
    render() {
        // 点击事件，handleClick处理点击事件，抽离处理函数，使得逻辑更清晰
        // 返回div组件，其中使用了state中的字段
        return (
            <div>
                <h1>计数器: {this.state.count} </h1>
                <button onClick={this.handleClick}>点击</button>
                <br/>

                <input type="text" name='txt' value={this.state.txt} onChange={this.handleForm}/> <br/>
                <input type='textarea' name='content' value={this.state.content} onChange={this.handleForm}/> <br/>
                <select name='city' value={this.state.city} onChange={this.handleForm}>
                    <option value='bj'>北京</option>
                    <option value='sh'>上海</option>
                    <option value='gz'>广州</option>
                </select> <br/>
                <input type='checkbox' name='isChecked' value={this.state.isChecked} onChange={this.handleForm}/> <br/>
            </div>
        )
    }
}

// 渲染DOM
ReactDOM.render(<Hello/>, document.getElementById('root'))