package OpenTutorials.java03_EffectiveRange;// 1. 유효범위
// 변수와 메소드에는 이름이 있다.
/*
* int left;
* public void sum(){}
* */
// left 는 변수 이름이고 sum 은 메소드 이름이다.
// 프로그램이 커지면 여러 가지 이유로 이름이 충돌하게 된다.
// 이를 해결하기 위해서 고안된 것이 유효범위라는 개념이다.
// 흔히 스코프(Scope) 라고 부른다.
// 유효범위의 출현 배경을 통해서
// 부품으로서의 로직이라는 가치가 얼마나 주요한 것인가를 환기해보자.

//2. 출현배경
// 메소드, 클래스와 같은 개념들이 등장한 배경은
// 프로그램을 만드는 데 사용하는 코드의 양이 가하급수적으로 증가하면서
// 직면하게되는 막장을 극복하기 위한 것이었다.
// 유효범위 역시 그러한 맥락에서 등장한 개념이다.
// 유효범위는 클래스나 메소드처럼 특별한 문법적인 규칙을 가지고 있지 않다.
// 오히려 메소드나 클래스 안에 포함되어서
// 이러한 기능들의 부품으로서의 가치를 높여주는 역할을 한다고 할 수 있다.

// Example.1
/*
public class ScopeDemo {

    static void a() {
        int i = 0;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            a();
            System.out.println(i);
        }
    }

}
*/
// 변수 i 의 값을 기준으로 동작하는 반복문이 있다.
// 다음으로 메소드 a를 호출하고 있고
// 메소드 a 의 내부에는 변수 i 값이 0 으로 지정되어 있다.
// 실행하면 0 1 2 3 4 5  를 출력한다.

// 만약 메소드 a 가 실행될 때 메소드 내부의 변수 i 의 값이
// 반복문 변수 i 값을 덮어쓰면 어떻게 될까?
// 반복문이 호출될 때마다 i 의 변수값이 0 이되기때문에 무한루프에 빠진다.
// 이런 상황을 해결하기 위해 메소드 a의 내부변수 i의 이름이나
// 변수 i 의 이름을 다르게 바꿔야 할 것이다.
// 로직이 매우 복잡하거나, 메소드 a 가 타인이 만든 것이라면 이것은 어려운 일이 된다.
// 이것이 부품으로서의 가치를 저하시킨다.
// 부품이란 조작 방법만 알면 내부의 동작 원리를 모르고도 사용할 수 있어야 한다.
// 또한, 부품 내부의 상태로 인해 그 부품을 사용하는 외부의 동작 방법에 영향을 준다면
// 그 또한 좋은 부품이라 할 수 없다.

// 실행결과 내부 변수의 값이 외부에 영향을 미치지 않는다는 것을 알 수 있다.
// 이러한 동작 방법은 수 많은 시행착오를 통해서 조율된 결과이다.
// 과거에는 메소드 내의 변수가 외부의 변수에 영향을 미쳤기 때문에
// 변수나 메소드의 이름을 사무실 칠판에 적어가며 코딩을 해야했던 시절도 있었다.

// 이러한 문제를 해결하기 위해 다양한 시도들이 있엇고 그중 하나가 유효범위 이다.

// 2. 다양한 유효범위들
// 디렉터리를 생각하면 쉽다.
// 처음에 파일이 있었고 -> 파일이 많아지면서 이름이 충돌하고
// -> 파일이름을 날짜, 부서, 이름 등을 적어 구분했다.
// -> 최종적으로 디렉터리를 만들게 되었다.
// 디렉터리는 파일을 그룹핑해서 그룹별로 파일을 격리한다.
// 디렉터리 내에서는 파일명이 중복되면 안되지만
// 디렉터리 밖의 파일명과는 중복이 돼도 문제 없다.
// Example.2
/*
public class ScopeDemo {
    static int i;

    static void a() {
        i = 0;
    }

    public static void main(String[] args) {
        for (i = 0; i < 5; i++) {
            a();
            System.out.println(i);
        }
    }

}
*/
// 위의 예제는 무한루프를 발생시킨다.
// 변수 i 가 static int i; 로 선언되어있다.
// i 는 스태틱변수 즉, 클래스 멤버이기 때문에
// 어떠한 메소드의 소속도 아닌 것이 된다.
// 클래스 멤버 변수가 되면 모든 메소드에 접근할 수 있게된다.
// 그래서 a() 내부의 i 변수는 클래스 멤버 변수 i 를 의미하게 된다.
// 마찬가지로 for 문 안에 i도 클래스 멤버 변수 i 를 의미하게 된다.
// a() 메소드의 i 와 for 문의 i 가 동시에 클래스 변수를 사용하게 된다는 의미이다.
// 반복문이 i 의 값을 아무리 바꿔도 a() 메소드에 의해 0이 되어버리므로
// 무한루프를 생성하게 되었다.
/*
    static void a() {
        int i = 0;
        }
*/
// a() 메소드 내부를 위와같이 변경해보자.
// 메소드만 놓고 봤을 때 메소드 안에서 선언한 변수는 그 메소드가 실행 될때 만들어지고,
// 그 메소드가 종료되면 삭제된다.
// 만약 클래스 아래의 변수와 메소드 아래의 변수가 같은 이름을 가지고 있다면
// 메소드 아래의 변수가 우선하게된다.
// 메소드 내의 변수가 존재하지 않을 때 클래스 아래의 변수를 사용하게 되는 것이다.

// 클래스 아래에 선언된 변수는 클래스 전역에 영향을 미치지만
// 메소드 내에서 선언된 변수는 클래스 아래에서 선언된 변수보다 우선순위가 높다.
// 지역적인 것이 전역적인 것 보다 우선순위가 높다는 것은
// 특수한 것이 전체적인 것보다 우선순위가 높다는 의미인데
// 이러한 원리는 공학 전반에서 적용되는 원칙이다.
// 전역적으로 기본값을 설정하고, 필요에 따라 지역값을 다르게 사용하는 것이 효율적이기 때문이다.
// 클래스 전역에서 사용하는 변수 -> 전역변수
// 메소드 내부에서 접근할 수 있는 변수 -> 지역번수

// 다음 예제는 지역변수가 메소드 내에서만 접근이 가능함을 보여준다.
// Example.3
/*
public class ScopeDemo {
    static void a(){
        String title = "coding everybody";
        System.out.println("in method -> "+title);
    }
    public static void main(String[] args) {
        a();
        //System.out.println(title);
    }
}
*/
// 130행의 주석을 제거하면 에러가 발생한다.
// title 변수는 a 내부에서만 유효하기 때문이다.

// Example.4
/*
public class ScopeDemo {
    static int i = 5;

    static void a() {
        int i = 10;
        b();
    }

    static void b() {
        System.out.println(i);
    }

    public static void main(String[] args) {
        a();
    }

}
*/
// 결과는 5이다.
// 메소드 a 가 메소드 b 를 호출하고 있는데
// 메소드 b 에는 변수 i의 값이 존재하지 않는다.
// 이 상태에서 메소드 a를 호출하면 메소드 b 에서 System.out.println(i)를 했을 때
// 클래스 변수를 사용하게 된다.
// 메소드 내부에 지역변수가 존재하지 않는다면 그 메소드가 소속된 클래스 전역 변수를 사용하게 된다.

// 이런 방식을 정적 스코프 (static scope) 혹은 lexical scope 라고 부른다.
// 사용되는 시점에서 가 아닌 정의된 시점에서 유효범위를 사용하는 것이다.
// 동적스코프도 있다. 자바는 동적 스코프를 채택하지 않고있다.
// 대부분 현대의 언어들은 정적 스코프 방식을 선택하고 있다.

// 3. 인스턴스의 유효범위
// 인스턴스에서의 유효범위도 클래스와 거의 동일하지만 결정적인 차이점은 this 에 있다.

// Example.5

class C {
    int v = 10;

    void m() {
        System.out.println(v);
    }

    void n(){
        int v = 20;
        System.out.println(v);
    }

    void mn(){
        int v =20;
        System.out.println(this.v);
        System.out.println(v);
    }
}

public class ScopeDemo {

    public static void main(String[] args) {
        C c1 = new C();
        c1.m();
        c1.n();
        c1.mn();
    }
}

// 결과는 10 20 10 20 이 출력된다.
// m 메소드는 전역변수를 출력했고 n 메소드는 지역변수를 출력하였다.
// mn 메소드는 둘다 출력하였는데 this.v 를 기억해두자.
// 지역변수가 선언된 메소드 내에서 전역변수를 사용하려면
// 위와같이 this 를 사용하면된다.
// this 는 인스턴스 자신을 의미하는 키워드라고 할 수 있다.

// 이렇게 유효범위란
// 변수를 전역변수, 지역변수로 나눠서 관리하기 편하도록 한 것이다.
// 객체의 개념이 없는 절차지향 프로그래밍에서는
// 모든 메소드에서 접근이 가능한 변수의 사용을 죄악시하는 경향이있다.
// 객체지향에서는 이런 문제를 극복하기 위한 노력이라고도 볼 수 있다.
// 연관된 변수와 메소드를 그룹핑 할 수 있도록 함으로서
// 좀더 만음놓고 객체 안에서 전역변수를 사용할 수 있도록 한 것이다.

// 어떤 메소드가 전역변수를 사용하고 있다는 것은
// 그 메소드는 그 전역변수에 의존한다는 의미다.
// 전역변수에 의존한다는 것은 이 메소드가 다른 완제품의 부품으로서 사용될 수 없다는 의미다.
// 객체도 크기가 커지면 관리의 이슈가 생겨난다.
// 객체 지향 프로그래밍 에서도 가급적이면 전역변수의 사용을 자제하는 것이 좋고
// 동시에 단일 객체가 너무 비대해지지 않도록 규모를 쪼개는 것도 중요하다.
