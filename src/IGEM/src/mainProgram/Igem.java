package mainProgram;

import codon.GeneFreMatrix;
import codon.NotSigmaSimilarity;
import codon.Primer;
import codon.Rbs;
import codon.SeqSimilarity;
import protein.Algorithm;
import protein.Algorithm2;

public class Igem {
	public static void main(String[] args)
	{
		//�����ӣ���������ģ��Ĳ��֣��㷨ʵ����codon.SeqSimilarity����
		String s1="AGATAGCGATGACGATGACGATAGCAG";		//������������
		String sigma1="";		//���ڱ������sigma����
		int bestStartPoint=0;		//���ڱ�����������ʼλ��
		int bestSpaceLength=0;			//���ڱ��������Ѽ�϶����
		double similarity=0.0;			//���ڱ��������漸����Ӧ�����ƶ�
		SeqSimilarity sigmaSimilarity=new SeqSimilarity(s1);			//�����㷨ʵ�֣��������
		sigma1=sigmaSimilarity.getName();	
		bestStartPoint = sigmaSimilarity.getBestStartPoint();
		bestSpaceLength = sigmaSimilarity.getBestSpaceLength();
		similarity = sigmaSimilarity.getSimilarity();
		
		//�������ągģ�����������У�����һ����ֵ�����
		double limit=0.0;				//���ƴ���һ��mSSֵ���Ǹ���
		String s2="AGATAGCGATGACGATGACGATAGCAG";		//������������
		NotSigmaSimilarity ns = new NotSigmaSimilarity(s2, limit);
		String[] name = ns.getNameArray();			//��������������ֱ������ơ���ʼλ�㡢���ƶ�
		int[] bestPoint = ns.getBestPointArray();
		double[] mSS = ns.getmSSArray();
		
		
		//�����������Ż�
		String s3="AGATAGCGATGACGATGACGATAGCAG";		//������������
		String[] cell={"ECOLI","PPASTORIS","LLACTIS","SCEREVISIZE"};
		int population=200;			//��Ⱥ��С
		int  daishu = 200;				//�Ŵ�����
		double crossRate=0.1;			//�������
		double varRate = 0.1;				//�������
		Algorithm al = new Algorithm(s3,cell[1],population,daishu,crossRate,varRate);
		String[] finalCodon = al.getFinalCodon();				//�������Ľ��
		
		
		//�����������Ż�2�����ϸ�һ��������ֱ�����ϸ�������
		Algorithm2 al2 = new Algorithm2(s3,cell[1],population,daishu,crossRate,varRate);
		String[] finalCodon2 = al2.getFinalCodon();			//�������Ľ��
		
		//Sc-Scideal�������
		String s4 = "AGATAGCGATGACGATGACGATAGCAG";		//������������
		Primer p = new Primer(s4);
		double[] min5 = p.getMin5();				//�õ���С��5��ֵ
		
		//rbs�����������str�������ĸ�ѡ����ѡһ��"ATG""TTG""GTG"null,matrix��ʱû�У�������ע�͵�
//		GeneFreMatrix matrix=null;
//		String s5 = "AGATAGCGATGACGATGACGATAGCAG";		//������������
//		String[] strs = {"ATG","TTG","GTG",null};
//		Rbs r = new Rbs(matrix, s5, strs[0]);
//		int bestStartPointOfRbs = r.getBestStartPoint();
//		int bestSpaceLengthOfRbs = r.getBestSpaceLength();
//		double bestmSS = r.getSimilarity();
	}
}
