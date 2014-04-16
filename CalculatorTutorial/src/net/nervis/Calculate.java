package net.nervis;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Calculate extends JFrame {
	private static final long serialVersionUID = 1L;

	JPanel contentPane = new JPanel();
	BorderLayout borderLayout1 = new BorderLayout();
	JTextField result = new JTextField(""); //�v�Z���ʂ�\������e�L�X�g�t�B�[���h
	double stackedValue = 0.0; //���Z�q�{�^���������O�Ƀe�L�X�g�t�B�[���h�ɂ������l
	boolean isStacked = false; //stackedValue�ɐ��l����͂������ǂ���
	boolean afterCalc = false; //���Z�q�{�^�����������ォ�ǂ���
	String currentOp = ""; //�����ꂽ���Z�q�{�^���̖��O

	//�t���[���̃r���h
	public Calculate() {
		contentPane.setLayout(borderLayout1);
		this.setSize(new Dimension(250, 300));
		this.setTitle("�d�q�����v�Z�@");
		this.setContentPane(contentPane);

		contentPane.add(result, BorderLayout.NORTH); //�e�L�X�g�t�B�[���h��z�u

		JPanel keyPanel = new JPanel(); //�{�^����z�u����p�l����p��
		keyPanel.setLayout(new GridLayout(4, 4)); //4�s4���GridLayout�ɂ���
		contentPane.add(keyPanel, BorderLayout.CENTER);

		keyPanel.add(new NumberButton("7"), 0); //�{�^�������C�A�E�g�ɂ͂߂���ł���
		keyPanel.add(new NumberButton("8"), 1);
		keyPanel.add(new NumberButton("9"), 2);
		keyPanel.add(new CalcButton("��"), 3);
		keyPanel.add(new NumberButton("4"), 4);
		keyPanel.add(new NumberButton("5"), 5);
		keyPanel.add(new NumberButton("6"), 6);
		keyPanel.add(new CalcButton("�~"), 7);
		keyPanel.add(new NumberButton("1"), 8);
		keyPanel.add(new NumberButton("2"), 9);
		keyPanel.add(new NumberButton("3"), 10);
		keyPanel.add(new CalcButton("�|"), 11);
		keyPanel.add(new NumberButton("0"), 12);
		keyPanel.add(new NumberButton("."), 13);
		keyPanel.add(new CalcButton("�{"), 14);
		keyPanel.add(new CalcButton("��"), 15);

		contentPane.add(new ClearButton(), BorderLayout.SOUTH);//C�{�^����z�u����
		this.setVisible(true);
	}

	/* �e�L�X�g�t�B�[���h�Ɉ����̕�������Ȃ��� */
	public void appendResult(String c) {
		if (!afterCalc) //���Z�q�{�^��������������łȂ��Ȃ�
			result.setText(result.getText() + c); //�������{�^���̖��O���Ȃ���
		else {
			result.setText(c); //�������{�^���̕����񂾂���ݒ肷��i��������N���A�������Ɍ�����j
			afterCalc = false;
		}
	}

	/* ��������͂���{�^���̒�` */
	public class NumberButton extends JButton implements ActionListener {
		private static final long serialVersionUID = 1L;

		public NumberButton(String keyTop) {
			super(keyTop); //JButton�N���X�̃R���X�g���N�^���Ăяo��
			this.addActionListener(this); //���̃{�^���ɃA�N�V�����C�x���g�̃��X�i��ݒ�
		}

		public void actionPerformed(ActionEvent evt) {
			String keyNumber = this.getText(); //�{�^���̖��O�����o��
			appendResult(keyNumber); //�{�^���̖��O���e�L�X�g�t�B�[���h�ɂȂ���
		}
	}

	/* ���Z�q�{�^�����` */
	public class CalcButton extends JButton implements ActionListener {
		private static final long serialVersionUID = 1L;

		public CalcButton(String op) {
			super(op);
			this.addActionListener(this);
		}

		public void actionPerformed(ActionEvent e) {
			if (isStacked) { //�ȑO�ɉ��Z�q�{�^���������ꂽ�̂Ȃ�v�Z���ʂ��o��
				double resultValue = (Double.valueOf(result.getText()))
						.doubleValue();
				if (currentOp.equals("�{")) //���Z�q�ɉ����Čv�Z����
					stackedValue += resultValue;
				else if (currentOp.equals("�|"))
					stackedValue -= resultValue;
				else if (currentOp.equals("�~"))
					stackedValue *= resultValue;
				else if (currentOp.equals("��"))
					stackedValue /= resultValue;
				result.setText(String.valueOf(stackedValue)); //�v�Z���ʂ��e�L�X�g�t�B�[���h�ɐݒ�
			}
			currentOp = this.getText(); //�{�^�������牟���ꂽ�{�^���̉��Z�q�����o��
			stackedValue = (Double.valueOf(result.getText())).doubleValue();
			afterCalc = true;
			if (currentOp.equals("��"))
				isStacked = false;
			else
				isStacked = true;
		}
	}

	/* �N���A�{�^���̒�` */
	public class ClearButton extends JButton implements ActionListener {

		private static final long serialVersionUID = 1L;

		public ClearButton() {
			super("C");
			this.addActionListener(this);
		}

		public void actionPerformed(ActionEvent evt) {
			stackedValue = 0.0;
			afterCalc = false;
			isStacked = false;
			result.setText("");
		}
	}
}