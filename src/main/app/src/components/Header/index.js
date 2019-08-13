import React from 'react';
import Clock from 'react-live-clock';

import './index.css';


class Header extends React.Component {
    constructor() {
        super()
        this.state = {
            lang: "",
            gifts: [],
            lodinggift: false,

        }
    }

    componentDidMount() {


        try {
            this.setState({ lodinggift: true })
            fetch('https://api.tenor.com/v1/categories?')
                .then(response => response.json())
                .then(data => {
                    console.log(data)
                    this.setState({
                        lang: data.locale,
                        gifts: data.tags,
                        lodinggift: false
                    })
                })
        } catch (e) {
            console.log("error when loading gif")
            console.log(e)
        }
    }
    render() {
        console.log(this.state.gifts)
        const giftssize = this.state.gifts.length
        const min = 0;
        const max = giftssize - 1;
        const rand = Math.floor(Math.random() * (max - min + 1) + min)
        const numb = 0 + rand;
        console.log("size is " + giftssize + "random number is " + numb)
        const gifi = this.state.gifts[numb]

        console.log(gifi)



        return (

            <header className="Header">
                <div className="App--contenst row">

                    <div className="App-content--left column1">
                        {this.state.lodinggift ? <div>Loading...</div> :
                            <div>
                                <img src="https://media.tenor.com/images/4aabd619b2e3273bc58594dfc00ccf50/tenor.gif" alt="Just smile"/>
                            </div>}
                        <Clock
                            format={'YYYY-MM-DD, h:mm:ss A'}
                            ticking={true}
                            timezone={'Europe/Vilnius'} />
                    </div>
                    <div className="App-content--right column2">
                        <h1 className="Header--title">Apie nieką</h1>
                        <hr className="Header--separator" />
                        <h2 className="Header--subtitle"> Sveiki atvykę į mano tiklaraštį</h2>
                    </div>
                </div>

            </header>
        )
    }
}
export default Header;
