package someothers;

//Ҫ������.txt���ļ����������¸�ʽ�������������������Ʊ��Ϊ�ָ�������������һϵ�еĴ���վ���������������ݣ���Ҫ�տ�ʼ��һЩ˵������
//���⣬����Ҫ�����Ʊ��Ϊ�ָ�����һ����ID�������������У��ӹ������ؼ���
//ͬʱ�ļ��в��ܳ��ֿ������

import java.io.*;

import codon.Matrix;

public class FindSeqTest {
	
	public static void main(String[] args)
	{
		try {
			BufferedReader txt = new BufferedReader(new FileReader("����.txt"));
			String str="";
			
			//�ȶ���sigma��������ģ���϶��С���ȣ�ģ���϶��󳤶ȣ���С��ʼλ�㣬�����ʼλ�㣨�Ӵ�д��ĸ��ʼ���������Ǹ�ֵ��
			str=txt.readLine();
			String baseInformation[]=str.split("\t");				//��baseInformation���������Ϣ������
			if(baseInformation.length!=5)
			{
				System.out.println("������Ϣ¼����������\"����.txt\"�ļ���");
				System.exit(0);
			}
			
			//�ȶ�����������
			str=txt.readLine();
			String[] strs=str.split("\t");
			int i=1;
			Matrix matrix1=new Matrix(4,strs.length);
			while(i<=4)
			{
				for(int j=1; j<=matrix1.getColumns(); j++)
					matrix1.setElement(i, j, Double.parseDouble(strs[j-1]));
				i++;
				str=txt.readLine();
				strs=str.split("\t");
			}						//�����ǶԵ�һ������Ķ���
			i=1;
			Matrix matrix2=new Matrix(4,strs.length);
			while(i<=4)
			{
				for(int j=1; j<=matrix2.getColumns(); j++)
					matrix2.setElement(i, j, Double.parseDouble(strs[j-1]));
				i++;
				str=txt.readLine();
				strs=str.split("\t");
			}					//�����ǶԵڶ�������Ķ��룬ע��˴��Ѿ����������еĵ�һ��
			Matrix resultMatrix1 = new Matrix(4,matrix1.getColumns());
			Matrix resultMatrix2 = new Matrix(4,matrix2.getColumns());
			//int idlength=strs[0].length();		//����ID����					������������ע������Ϊ֮��û�õ�
			//int genelength=strs[5].length();  //���ڱ������г���
			String ID=strs[0];  //���ڱ���ID
			String gene=strs[5];		//���ڱ�������������
			//int genePosition=0;		//���п�ʼλ��
			while(str!=null&&!str.isEmpty())
			{
				strs=str.split("\t");
				ID = strs[0];
				gene = strs[5];
				if(gene.isEmpty())
				{
					str=txt.readLine();
					continue;
				}
				FindSth findSimilarity = new FindSth(gene,matrix1,matrix2,Integer.parseInt(baseInformation[1]),Integer.parseInt(baseInformation[2]),Integer.parseInt(baseInformation[3]),Integer.parseInt(baseInformation[4]));
				System.out.println("Result:\n"+"ID: "+ID+";  ���У�"+gene+";\n�����ʼλ�㣺"+findSimilarity.getBestStart()+";  ��Ѽ�϶��"+findSimilarity.getBestSpace()+";  ���ƶȣ�"+findSimilarity.getSimilarity()+";\n\n");
				resultMatrix1.add(findSimilarity.getMatrix1());
				resultMatrix2.add(findSimilarity.getMatrix2());
				str = txt.readLine();
			}
			txt.close();
			System.out.println("ģ�������Ϣ��");
			System.out.println("ģ�����ƣ�"+baseInformation[0]+",��С��϶��"+baseInformation[1]+",����϶��"+baseInformation[2]+",��С��ʼλ�㣺"+baseInformation[3]+",�����ʼλ�㣺"+baseInformation[4]);
			System.out.println("��һ��ģ�壺");
			resultMatrix1.print();
			System.out.println("\n\n�ڶ���ģ�壺");
			resultMatrix2.print();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
