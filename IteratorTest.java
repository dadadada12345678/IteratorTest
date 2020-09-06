package kadai07;

interface Iterator {
    public void first();     // ���o���ʒu���ŏ��̗v�f�֕ς���
    public void next();      // ���o���ʒu�����̗v�f�֕ς���
    public boolean isDone(); // ���o���ʒu���Ō�𒴂������H
    public Object getItem(); // ���݂̎��o���ʒu������o��
}

class GameListIterator implements Iterator {
    private GameListAggregate aggregate;
    private int current;

    public GameListIterator(GameListAggregate aggregate) {
        this.aggregate = aggregate;
    }

    @Override
    public void first() {
        current = 0;
    }

    @Override
    public void next() {
        current += 1;
    }

    @Override
    public boolean isDone() {
        if (current >= aggregate.getNumberOfStock()) {
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public Object getItem() {
        return aggregate.getAt(current);
    }
}


interface Aggregate {
    public Iterator createIterator();
}


class GameListAggregate implements Aggregate {
    private Game[] list = new Game[20];
    private int numberOfStock;

    @Override
    public Iterator createIterator() {
        return new GameListIterator(this);
    }
    public void add(Game game) {
        list[numberOfStock] = game;
        numberOfStock += 1;
    }
    public Object getAt(int number) {
        return list[number];
    }
    public int getNumberOfStock() {
        return numberOfStock;
    }
}


public class IteratorTest {
    public static void main(String[] args) {
        GameListAggregate gameListAggregate = new GameListAggregate();
        Iterator iterator = gameListAggregate.createIterator();
        gameListAggregate.add(new Game("�݂�ȂŃS���t", 3700));
        gameListAggregate.add(new Game("�t�@�C�i���t�@���^�W�A", 7300));
        gameListAggregate.add(new Game("���P�b�g�����X�^�[", 5400));
        gameListAggregate.add(new Game("�T�C�R���̒B�l", 5200));

        iterator.first(); // �܂��T���ꏊ��擪�ʒu�ɂ��Ă��炤
        while ( ! iterator.isDone() ) { // �܂�����H �܂������I
            Game game = (Game)iterator.getItem(); // �͂��ǂ��� (�Ǝ���)
            System.out.println(game.getName());
            iterator.next(); // ���𒸑�
        }
    }
}


class Game {
    private String name;  // ����
    private int price;    // ���i

    public Game(String name, int price) { // �R���X�g���N�^
        this.name= name;
        this.price = price;
    }
    public String getName() { // ���̂��擾
        return name;
    }
    public int getPrice() { // ���i���擾 
        return price;
    }
}
