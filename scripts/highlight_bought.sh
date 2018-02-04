./backup.sh

BASE_DIR='/home/yifeirock/Projects/funds'
PROCESSOR_DIR=$BASE_DIR'/java/fundsProcessor/out/production/fundsProcessor/'
EXCEL_FILE=$BASE_DIR'/funds.xlsx'
LIB_DIR=$BASE_DIR'/java/poi-bin-3.16-20170419/poi-3.16/'
BOUGHT=`cat $BASE_DIR'/scripts/bought.txt'`

CLASSPATH='.'

L1=$LIB_DIR'*.jar'
for i in `ls $L1`
do
CLASSPATH=$CLASSPATH":"$i
done

L2=$LIB_DIR'lib/*.jar'
for i in `ls $L2`
do
CLASSPATH=$CLASSPATH":"$i
done

L3=$LIB_DIR'ooxml-lib/*.jar'
for i in `ls $L3`
do
CLASSPATH=$CLASSPATH":"$i
done

cd $PROCESSOR_DIR
java -Xms4g -Xmx4g -cp $CLASSPATH':.' BoughtProcessor $EXCEL_FILE $BOUGHT
