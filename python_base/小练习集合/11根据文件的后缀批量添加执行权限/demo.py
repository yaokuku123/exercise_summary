import os
import stat

if __name__ == '__main__':
    # 读取当前路径下的全部文件名列表
    d = os.listdir('.')
    for f in d:
        # 文件名是否以.sh或者.java结尾
        if f.endswith(('.sh', '.java')):
            # 获取文件的状态信息
            f_stat = os.stat(f)
            # 修改文件user的权限，增加执行权限
            os.chmod(f, f_stat.st_mode & stat.S_IXUSR)
