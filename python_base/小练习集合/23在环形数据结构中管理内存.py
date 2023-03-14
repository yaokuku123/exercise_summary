import weakref
import time


# 创建双端链表
class Node(object):
    def __init__(self, data):
        self.data = data
        self._left = None
        self.right = None

    # 使得left弱引用与right普通引用的获取形式一致
    @property
    def left(self):
        # 通过弱引用创建一个普通引用
        return self._left()

    def add_right(self, node):
        self.right = node
        # 左边的引用改为弱引用
        node._left = weakref.ref(self)

    def __str__(self):
        return f'node {self.data}'

    def __del__(self):
        print(f'node {self.data} del...')


# 创建一个双端链表
def get_node_list():
    head = current = Node(1)
    for i in range(2, 101):
        node = Node(i)
        current.add_right(node)
        current = node
    return head


if __name__ == '__main__':
    node = get_node_list()
    # 通过弱引用获取一个普通引用
    head_left = node.right.left
    print(head_left)
    # 注意：即使释放node引用，但若该head_left引用不释放，依据无法释放环形数据的内存
    head_left = None
    # 释放头节点的引用，造成连锁反应，释放环形数据内存
    node = None

    for i in range(100):
        time.sleep(1)
        print('run...')
