package com.example.wicket.demo.examples.tables;

import lombok.Builder;
import lombok.Data;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.extensions.markup.html.repeater.data.table.*;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class TablePage extends WebPage {


  @Data
  @Builder
  private static class TableDataBean implements Serializable {

    private String col1;
    private String col2;
    private String col3;
    private String col4;

  }

  private List<TableDataBean> data;

  public TablePage() {
    data = new ArrayList<>(List.of(
        TableDataBean.builder().col1("John").col2("Smith").col3("New York").col4("4000").build(),
        TableDataBean.builder().col1("Mary").col2("Meyer").col3("Dallas").col4("2300").build(),
        TableDataBean.builder().col1("Bob").col2("King").col3("Miami").col4("90").build(),
        TableDataBean.builder().col1("Rose").col2("Burns").col3("Boston").col4("43000").build()
    ));
  }

  @Override
  protected void onInitialize() {
    super.onInitialize();
    add(newListView("list"));
    add(newTable("table"));
  }

  private Component newListView(String id) {
    return new ListView<>(id, () -> data) {
      @Override
      protected void populateItem(ListItem<TableDataBean> item) {
        TableDataBean bean = item.getModelObject();
        item.add(new Label("col1", bean.col1));
        item.add(new Label("col2", bean.col2));
        item.add(new Label("col3", bean.col3));
        item.add(new Label("col4", bean.col4));
        item.add(AttributeModifier.replace("class",
            item.getIndex() % 2 == 0 ? "" : "bg-gray-100"));
      }


    };
  }

  private Component newTable(String id) {

    List<IColumn<TableDataBean, String>> columns = List.of(
        new PropertyColumn<>(() -> "col1 label", "col1", "col1"),
        new PropertyColumn<>(() -> "col2 label", "col2", "col2"),
        new PropertyColumn<>(() -> "col3 label", "col3", "col3"));

    SortableDataProvider<TableDataBean, String> provider = new SortableDataProvider<>() {
      @Override
      public Iterator<TableDataBean> iterator(long first, long count) {
        ArrayList<TableDataBean> tmp = Optional.ofNullable(getSort())
            .map(sort -> {
              String property = sort.getProperty();
              boolean ascending = sort.isAscending();
              ArrayList<TableDataBean> copy = new ArrayList<>(data);
              copy.sort((o1, o2) -> {
                String d1 = PropertyModel.<String>of(o1, property).getObject();
                String d2 = PropertyModel.<String>of(o2, property).getObject();
                return d1.compareTo(d2) * (ascending ? 1 : -1);
              });
              return copy;
            }).orElseGet(() -> new ArrayList<>(data));
        return tmp.subList((int) first, (int) (first + count)).iterator();
      }

      @Override
      public long size() {
        return data.size();
      }

      @Override
      public IModel<TableDataBean> model(TableDataBean object) {
        return () -> object;
      }

    };

    DataTable<TableDataBean, String> table = new DataTable<>(
        id,
        columns,
        provider,
        2
    ) {
      @Override
      protected Item<TableDataBean> newRowItem(String id, int index, IModel<TableDataBean> model) {
        Item<TableDataBean> item = super.newRowItem(id, index, model);
        item.add(AttributeModifier.replace("class",
            item.getIndex() % 2 == 0 ? "" : "bg-gray-100"));
        return item;
      }

      @Override
      protected Item<IColumn<TableDataBean, String>> newCellItem(String id, int index, IModel<IColumn<TableDataBean, String>> model) {
        Item<IColumn<TableDataBean, String>> item = super.newCellItem(id, index, model);
        item.add(AttributeModifier.replace("class", "border px-8 py-4"));
        item.add(AttributeModifier.append("class",
            item.getIndex() % 2 == 0 ? "" : "bg-red-100"));
        return item;
      }
    };
    table.add(AttributeModifier.replace("class", "table-auto"));

    table.addTopToolbar(new HeadersToolbar<>(table, provider));
    table.addBottomToolbar(new NavigationToolbar(table));

    return table;


  }

}
