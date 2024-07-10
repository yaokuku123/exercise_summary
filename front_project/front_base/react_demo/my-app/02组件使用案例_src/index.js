import React from 'react'
import ReactDOM from 'react-dom'

/* 
  评论列表案例

  comments: [
    { id: 1, name: 'jack', content: '沙发！！！' },
    { id: 2, name: 'rose', content: '板凳~' },
    { id: 3, name: 'tom', content: '楼主好人' }
  ]
*/

import './index.css'

class App extends React.Component {
    state = {
        comments: [
            {id: 1, name: 'jack', content: '沙发！！！'},
            {id: 2, name: 'rose', content: '板凳~'},
            {id: 3, name: 'tom', content: '楼主好人'}
        ],
        userName: '',
        userContent: ''
    }

    getComments = () => {
        if (this.state.comments.length === 0) {
            return (
                <div className="no-comment">暂无评论，快去评论吧~</div>
            )
        }
        return (
            <ul>
                {this.state.comments.map((comment) => {
                    return (
                        <li key={comment.id}>
                            <h3>评论人：{comment.name}</h3>
                            <p>评论内容：{comment.content}</p>
                        </li>
                    )
                })}
            </ul>
        )
    }

    handleForm = e => {
        const {name, value} = e.target
        this.setState({
            [name]: value
        })
    }

    handleClick = e => {
        const {comments, userName, userContent} = this.state
        if (userName.trim() === '' || userContent.trim() === '') {
            alert("请输入用户名和评论内容")
            return
        }
        const newComments = [{id: Math.random(), name: userName, content: userContent}, ...comments]
        this.setState({
            comments: newComments,
            userName: '',
            userContent: ''
        })
    }

    render() {
        const {userName, userContent} = this.state
        return (
            <div className="app">
                <div>
                    <input className="user" type="text" placeholder="请输入评论人" name='userName'
                           value={userName} onChange={this.handleForm}/>
                    <br/>
                    <textarea
                        className="content"
                        cols="30"
                        rows="10"
                        placeholder="请输入评论内容"
                        name='userContent'
                        value={userContent}
                        onChange={this.handleForm}
                    />
                    <br/>
                    <button onClick={this.handleClick}>发表评论</button>
                </div>

                {this.getComments()}
            </div>
        )
    }
}

// 渲染组件
ReactDOM.render(<App/>, document.getElementById('root'))
