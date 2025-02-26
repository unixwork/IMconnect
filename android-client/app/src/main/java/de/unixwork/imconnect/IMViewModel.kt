package de.unixwork.imconnect

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel


class IMViewModel : ViewModel() {
    var connections by mutableStateOf(mutableListOf<Connection>())

    init {
        // initialize some test data

        val conn1 = Connection("Connection 1")
        val conn2 = Connection("Connection 2")
        connections.add(conn1)
        connections.add(conn2)

        val conv1 = Conversation("Alice")
        conv1.addMessage("Hi", true)
        conv1.addMessage("Hello", false)
        conv1.addMessage("How are you?", true)

        val conv2 = Conversation("Bob")
        conv2.addMessage("Hello Bob", false)

        conn1.addConversation(conv1)
        conn1.addConversation(conv2)
    }

    fun getConnection(name: String): Connection? {
        return connections.find { conn -> conn.name.equals(name) }
    }
}

class Connection(connectionName: String) {
    var name: String by mutableStateOf(connectionName)
    var conversations = mutableStateListOf<Conversation>()

    fun addConversation(conversation: Conversation) {
        conversations.add(conversation)
    }

    fun getConversation(name: String): Conversation? {
        return conversations.find { conv -> conv.name.equals(name) }
    }
}

class Conversation(conversationName: String) {
    var name: String by mutableStateOf(conversationName)
    var messages = mutableStateListOf<Message>()

    fun addMessage(text: String, incoming: Boolean) {
        var msg = Message()
        msg.incoming = incoming
        msg.text = text
        messages.add(msg)
    }
}

class Message {
    var incoming by mutableStateOf(false)
    var text by mutableStateOf("")
}