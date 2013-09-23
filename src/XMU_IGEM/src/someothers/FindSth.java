package someothers;

import codon.Matrix;

public class FindSth {
	
	//���캯��
	public FindSth(String gene, Matrix motif_1, Matrix motif_2, int minSpace, int maxSpace, int minStart, int maxStart)
	{
		this.getSimilarity(gene, motif_1, motif_2, minSpace, maxSpace, minStart, maxStart);
		this.matrix1 = getMatrix(gene, motif_1.getColumns(), bestStart-1);
		this.matrix2 = getMatrix(gene, motif_2.getColumns(), bestStart+motif_1.getColumns()+bestSpace-1);
	}
	
	//toString����
	public String toString()
	{
		return "Result��\n"+"�����ʼλ�㣺  "+this.bestStart+";\n��Ѽ�϶��  "+this.bestSpace+";\n���ƶȣ�  "+this.Similarity;
	}
	
	//��ȡ�����ʼλ�㣬��Ѽ�϶�����ƶ�
	public int getBestSpace() {
		return bestSpace;
	}

	public int getSimilarity() {
		return Similarity;
	}

	public int getBestStart() {
		return bestStart;
	}

	public Matrix getMatrix1() {
		return matrix1;
	}

	public Matrix getMatrix2() {
		return matrix2;
	}

	//����һ������Ϊģ�峤�ȵ����У��������ƣ�ͬʱ����һ��Ƶ�����󣨴˴�֮ǰ��ȷ��ģ�壬������ȷ�����ַ�����
	private int getSingleSimilarity(String gene, Matrix motif)
	{
		int similarity = 0;
		if(gene.length() != motif.getColumns())
		{
			System.out.println("wrong at GetSingleSimilarity!!");
			return similarity;
		}
		
		for(int i=0; i<gene.length(); i++)
		{
			switch(gene.charAt(i))
			{
			case 'a':
				similarity+=motif.getElement(1, i+1);
				break;
			case 'g':
				similarity+=motif.getElement(2, i+1);
				break;
			case 'c':
				similarity+=motif.getElement(3, i+1);
				break;
			case 't':
				similarity+=motif.getElement(4, i+1);
				break;
			case 'u':
				similarity+=motif.getElement(4, i+1);
				break;
			}
		}
		
		return similarity;
	}
	
	//��ȡ����ģ�峤�ȵ����ƶ�
	private int getDoubleSimilarity(String gene1, String gene2, Matrix motif_1, Matrix motif_2)
	{
		int similarity = 0;
		if(gene1.length()!=motif_1.getColumns() || gene2.length()!=motif_2.getColumns())
		{
			System.out.println("wrong at GetDoubleSimilarity!!");
			return similarity;
		}
		
		return getSingleSimilarity(gene1,motif_1)+getSingleSimilarity(gene2,motif_2);
	}
	
	//���Ǽ�϶����
	private int getSimilarityKnownStart(String gene, Matrix motif_1, Matrix motif_2, int minSpace, int maxSpace)
	{
		int similarity = 0;
		int bestspace = minSpace;
		
		for(int i=minSpace; i<=maxSpace && motif_1.getColumns()+i+motif_2.getColumns()<=gene.length(); i++)
		{
			if(similarity < getDoubleSimilarity(gene.substring(0, motif_1.getColumns()), gene.substring(0+motif_1.getColumns()+i, 0+motif_1.getColumns()+i+motif_2.getColumns()), motif_1, motif_2))
			{
				similarity = getDoubleSimilarity(gene.substring(0, motif_1.getColumns()), gene.substring(0+motif_1.getColumns()+i, 0+motif_1.getColumns()+i+motif_2.getColumns()), motif_1, motif_2);
				bestspace = i;
			}
		}
		
		if(this.Similarity < similarity)
		{
			this.bestSpace = bestspace;
		}
		
		return similarity;
	}
	
	//������ʼλ��
	private void getSimilarity(String gene, Matrix motif_1, Matrix motif_2, int minSpace, int maxSpace, int minStart, int maxStart)
	{
		int similarity = 0;
		int zeroPoint = 0; //��д��ĸλ��
		for(int i=0; i<gene.length(); i++)
			if(gene.charAt(i)>='A' && gene.charAt(i)<='Z')
				zeroPoint = i;
		for(int i=zeroPoint+minStart; i<=zeroPoint+maxStart; i++)
		{
			similarity = getSimilarityKnownStart(gene.substring(i, zeroPoint), motif_1, motif_2, minSpace, maxSpace);
			if(similarity > this.Similarity)
			{
				this.Similarity = similarity;
				this.bestStart = i+1;
			}
		}
	}
	
	//ȡ��ÿ��λ��Ƶ�����Ǹ���ά����
	private Matrix getMatrix(String gene, int length, int startPoint)
	{
		Matrix matrix = new Matrix(4,length);
		for(int j=1; j<=length; j++)
			switch(gene.charAt(startPoint-1+j-1))
			{
			case 'a':
				matrix.setElement(1, j, 1);
				break;
			case 'g':
				matrix.setElement(2, j, 1);
				break;
			case 'c':
				matrix.setElement(3, j, 1);
				break;
			case 't':
				matrix.setElement(4, j, 1);
				break;
				default:
					System.out.println("wrong at getMatrix!");
			}
		return matrix;
	}
	
	//��
	private Matrix matrix1;
	private Matrix  matrix2;
	private int bestSpace; //��Ѽ�϶����
	private int Similarity;
	private int bestStart;
}
