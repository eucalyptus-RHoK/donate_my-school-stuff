(function () {
  search = {
    name: '',
    category: '',
    school: ''
  };

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
      var self = this
        , url = '/interface/search/';
      $.get(url, function (data) {
        data = $.parseJSON(data);
        console.log(this.el, this.$el)
//        console.log(data)
        self.$el.html(
          _.template(
            $("#sidebar-template").html(),
            {data: data}
          )
        );
      });
    }
  });

  AddView = Backbone.View.extend({
    el: "#homepage",
    initialize: function () {
      this.render();
    },
    events: {
      'click button': 'submit'
    },
    submit: function () {
      var base64 = $('#image-src').val().split(',')[1]
        , image = $('#image').val().split('.');

      $.post('/interface/publish/', {
        userID: $('#userID').val(),
        image: base64,
        image_ext: image[image.length - 1],
        objectName: $('#item').val(),
        description: $('#description').val(),
        school: $('#school').val(),
        category: $('#category').val()
      });

      console.log('submit')
    },
    render: function () {
      var self = this;
      $('#data-panel').hide();
      $('#homepage').show();

      $.get('/interface/bootstrap/', function (data) {
        data = $.parseJSON(data);
        self.$el.html(_.template($("#add-template").html(), {
          schools: data.schools,
          categories: data.categories
        }));

        function fileOnload(e) {
          $("#image-src").val(e.target.result);
        }

        $('#image').change(function (e) {
          var file = e.target.files[0],
            imageType = /image.*/;

          if (!file.type.match(imageType))
            return;

          var reader = new FileReader();
          reader.onload = fileOnload;
          reader.readAsDataURL(file);
        });

      });

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