import React from 'react';
import axios from 'axios';

class PubListItemComponent extends React.Component {

    render = () => {
        let button = this.props.loggedIn && !this.props.pub.visited ?
            <button onClick={e => this.visitPub(this.props.pub.id)}>Been!</button> : "";
        return <li key={this.props.pub.id}>
            <h2>{this.props.pub.name}</h2>
            {this.status()}
            {button}
        </li>;
    };

    visitPub = (id) => {
        axios.post(`/pubs/visit/${id}`)
            .then(this.props.refresh);
    };

    status = () => {
        if (this.props.loggedIn) {
            return this.props.pub.visited ? "Visited" : "Not Visited";
        }

    }

}

export default PubListItemComponent;