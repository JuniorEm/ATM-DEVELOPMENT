package br.umc.view.option;

public enum OptionEnum {
	CONSULT("1"), TAKE("2"), DEPOSIT("3"), EXIT("4");
	
	private String code;
	
	OptionEnum(final String code) {
		this.code = code;
	}
}
