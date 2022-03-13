package Interpret;

/**
 * 环境类
 */
class InstructionHandler {
    private String instruction;
    private AbstractNode node;

}

/**
 * 抽象表达式
 */
abstract class AbstractNode {

}

/**
 * 终结符表达式
 */
class DirectionNode extends AbstractNode {

}
class ActionNode extends AbstractNode {

}
class DistanceNode extends AbstractNode {

}

/**
 * 非终结符表达式
 */
class AndNode extends AbstractNode {

}
class SentenceNode extends AbstractNode {

}
