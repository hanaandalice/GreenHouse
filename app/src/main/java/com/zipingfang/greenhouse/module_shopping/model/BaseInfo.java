package com.zipingfang.greenhouse.module_shopping.model;


public class BaseInfo
{
	protected String Id;
	protected String name;
	protected boolean isChoosed;
	protected boolean isEditChoosed;

	public BaseInfo()
	{
		super();
	}

	public BaseInfo(String id, String name)
	{
		super();
		Id = id;
		this.name = name;

	}

	public String getId()
	{
		return Id;
	}

	public void setId(String id)
	{
		Id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public boolean isEditChoosed() {
		return isEditChoosed;
	}

	public void setEditChoosed(boolean editChoosed) {
		isEditChoosed = editChoosed;
	}

	public boolean isChoosed(){
		return isChoosed;
	}

	public void setChoosed(boolean isChoosed)
	{
		this.isChoosed = isChoosed;
	}

}
