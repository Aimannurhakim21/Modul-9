public class ConditionalExpression {
    if (score > 700)
        accept();
    else if ((income >= 40000) && (income <= 100000)
            && authorized && (score > 500))
        accept();
    else if (income > 100000)
        accept();
    else
        reject();
}
