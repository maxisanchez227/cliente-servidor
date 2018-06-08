import React, { Component } from 'react';
import ReactDOM from 'react-dom';
import axios from 'axios';
import { Link } from 'react-router-dom';

class Create extends Component {

  constructor() {
    super();
    this.state = {
      isbn: '',
      title: '',
      author: '',
      description: '',
      published_year: '',
      publisher: ''
    };
  }
  onChange = (e) => {
    const state = this.state
    state[e.target.name] = e.target.value;
    this.setState(state);
  }

  onSubmit = (e) => {
    e.preventDefault();

    const { isbn, title, author, description, published_year, publisher } = this.state;

    axios.post('/api/book', { isbn, title, author, description, published_year, publisher })
      .then((result) => {
        this.props.history.push("/")
      });
  }

  render() {
    const { isbn, title, author, description, published_year, publisher } = this.state;
    return (
      <div class="container">
        <div class="panel panel-default">
          <div class="panel-heading">
            <h3 class="panel-title">
              AGREGAR LIBRO
            </h3>
          </div>
          <div class="panel-body">
            <h4><Link to="/"><span class="glyphicon glyphicon-th-list" aria-hidden="true"></span> Lista de Libros</Link></h4>
            <form onSubmit={this.onSubmit}>
              <div class="form-group">
                <label for="isbn">ISBN:</label>
                <input type="text" class="form-control" name="isbn" value={isbn} onChange={this.onChange} placeholder="ISBN" />
              </div>
              <div class="form-group">
                <label for="title">Título:</label>
                <input type="text" class="form-control" name="title" value={title} onChange={this.onChange} placeholder="Título" />
              </div>
              <div class="form-group">
                <label for="author">Autor:</label>
                <input type="text" class="form-control" name="author" value={author} onChange={this.onChange} placeholder="Autor" />
              </div>
              <div class="form-group">
                <label for="description">Descripción:</label>
                <textArea class="form-control" name="description" onChange={this.onChange} placeholder="Descripción" cols="80" rows="3">{description}</textArea>
              </div>
              <div class="form-group">
                <label for="published_date">Fecha de Publicación:</label>
                <input type="number" class="form-control" name="published_year" value={published_year} onChange={this.onChange} placeholder="Fecha de Publicación" />
              </div>
              <div class="form-group">
                <label for="publisher">Editor:</label>
                <input type="text" class="form-control" name="publisher" value={publisher} onChange={this.onChange} placeholder="Editor" />
              </div>
              <button type="submit" class="btn btn-default">Enviar</button>
            </form>
          </div>
        </div>
      </div>
    );
  }
}

export default Create;
