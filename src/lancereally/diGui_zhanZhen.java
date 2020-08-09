package lancereally;

public class diGui_zhanZhen {
    //输入一个链表，按链表从尾到头的顺序返回一个ArrayList
    //https://www.nowcoder.com/practice/d0267f7f55b3412ba93bd35cfa8e8035?tpId=13&tqId=11156&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
    //https://blog.nowcoder.net/n/b30ce013bd294a1681711e8a6f0a4231?f=comment
    /**1. 通过List的add（0，value）不断重置第一个元素，其余元素后移*/

    /**2. 利用递归方法的底层栈帧实现的先入后出特性，用add（value）*/
}

class myStack{
    Object data;
    myStack next = null;
    public myStack(Object data){
        this.data = data;
    }
}