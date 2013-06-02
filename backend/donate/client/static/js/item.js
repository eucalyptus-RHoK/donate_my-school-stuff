(function () {
  WelcomeView = Backbone.View.extend({
    el: "#homepage",
    initialize: function () {
      this.render();
    },
    render: function () {
      $('#data-panel').hide();
      $('#homepage').show();
      this.$el.html(_.template($("#welcome-template").html()));
    }
  });

  SideBarView = Backbone.View.extend({
    el: "#list",
    initialize: function () {
      this.render();
    },
    render: function () {
      this.$el.html(_.template($("#sidebar-template").html()));
    }
  });

  SearchView = Backbone.View.extend({
    el: "#right-panel",
    initialize: function () {
      this.render();
    },
    render: function () {
      var self = this;
      $('#data-panel').show();
      $('#homepage').hide();
      new SideBarView();

      $.get('/interface/bootstrap/', function (data) {
        data = $.parseJSON(data);
        self.$el.html(_.template($("#search-template").html(), {
          schools: data.schools,
          categories: data.categories
        }));
      })
    }
  });

  new WelcomeView();
})();

$('.js-search-item').on('click', function () {
  new SearchView();
});

$('.js-add-item').on('click', function () {
  new AddView();
});

$('.js-pubblication-item').on('click', function () {
  new PubblicatinView();
});