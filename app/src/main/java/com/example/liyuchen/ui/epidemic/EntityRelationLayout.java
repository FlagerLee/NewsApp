package com.example.liyuchen.ui.epidemic;

import androidx.recyclerview.widget.RecyclerView;

public class EntityRelationLayout{
    public String name;
    public String relation;
    public String symptom;

    public EntityRelationLayout(String _name, String _relation, String _symptom) {
        this.name = _name;
        this.relation = _relation;
        this.symptom = _symptom;
    }
}
