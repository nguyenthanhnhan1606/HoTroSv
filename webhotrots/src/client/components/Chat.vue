<template>
 <div class="container bg-new py-5 px-4" v-if="this.getUser != null">

  <div class="row rounded-lg overflow-hidden shadow">
    <!-- Users box-->
    <div class="col-5 px-0">
      <div class="bg-white">

        <div class="bg-gray px-4 py-2 bg-light">
          <p class="h5 mb-0 py-1">Các tư vấn của trường</p>
        </div>

        <div class="messages-box" v-if="lstAdviser != null">
          <div class="list-group rounded-0">
              <a class="list-group-item list-group-item-action clickable active text-white rounded-0"
                  @click="handleChatBotClick()">
                    <div class="media">
                      <img src="@/assets/avatar_chatbot.png" alt="user" width="50" class="rounded-circle">
                      <div class="media-body ml-4">
                    <div class="d-flex align-items-center justify-content-between mb-1">
                      <h6 class="mb-0 text-dark">Trợ lý ảo</h6>
                    <small class="small font-weight-bold">25 Dec</small>
                  </div>
                  <p class="font-italic mb-0 text-small">Nhấn để xem cuộc trò chuyện</p>
                </div>
          </div>
        </a>
          </div>
           <div class="list-group rounded-0" v-for="adviser in lstAdviser" :key="adviser.id">
              <a class="list-group-item list-group-item-action clickable active text-white rounded-0"
              @click="handleAdviserClick(adviser)">
                <div class="media">
                   <img :src="adviser.avatar" alt="user" width="50" class="rounded-circle">
                   <div class="media-body ml-4">
                 <div class="d-flex align-items-center justify-content-between mb-1">
                  <h6 class="mb-0 text-dark">{{ adviser.name }}</h6>
                 <small class="small font-weight-bold">25 Dec</small>
              </div>
               <p class="font-italic mb-0 text-small">Nhấn để xem cuộc trò chuyện</p>
            </div>
      </div>
    </a>
  </div>
</div>
      </div>
    </div>
    <!-- Chat Box-->
    <div class="col-7 px-0" v-if="this.adviser!=null">
      <div class="bg-gray px-4 py-2 bg-light" style="display: flex; margin: 0; height:48px">
          <p class="h5 mb-0 py-1" style="width: 320px;">{{ adviser.name }} </p>
          <p style="margin-left: 200px;"> <i class="fas fa-circle text-success"></i> ĐANG HOẠT ĐỘNG</p>
        </div>
      <div class="px-4 py-5 chat-box bg-white">
        <!-- Sender Message-->
        <div v-for="(msg, index) in messages" v-bind:key="'index-'+index"
             :class="['message', sentOrReceived(msg.id)]">
          <img :src="msg.avatar" alt="avatar">
          <p>{{ msg.content }}</p>
        </div> 
        <div class="messages-box-scroll" ref="scrollableNew"/>
      </div>
      <!-- Typing area -->
      <form @submit.prevent="sendMessage()" class="bg-light">
        <div class="input-group">
          <input type="text" v-model="message" placeholder="Type a message" aria-describedby="button-addon2" class="form-control rounded-0 border-0 py-4 bg-light">
          <div class="input-group-append">
            <button id="button-addon2" type="submit" class="btn btn-link" :disabled="isMessageEmpty"> <i class="fa fa-paper-plane"></i></button>
          </div>
        </div>
      </form>

    </div>
    <div class="col-7 px-0 align-items-center" v-else>
      <div class="px-4 py-5 chat-box bg-white" > 
        <div v-if="this.getUser.userRole == 'ROLE_USER'">
          <p class="text-center text-danger">CHÀO BẠN <span class="text-success">{{this.getUser.name}}</span>!! Bạn cần biết thông tin gì hãy liên hệ với ban cố vấn của trường</p>
        </div> 
        <div v-else>
          <p class="text-center text-danger">CHÀO BẠN <span class="text-success">{{this.getUser.name}}</span>!! Bạn hãy trả lời các tin nhắn</p>
        </div> 
      </div>  
    </div> 
  </div>
</div>
</template>

<script>
import { database, ref, push } from '@/configs/Firebase';
import { onValue } from 'firebase/database';
import { mapGetters } from 'vuex';
import Apis, { authApi, endpoints} from '@/configs/Apis';
import axios from 'axios';

export default {
    name: "Chat",
    data() {
        return {
          message: "",
          messages: [],
          lstAdviser: null,
          groupChat: "",
          adviser:null,
          lstIdUser: [],
          bot:{
            avatar:"https://res.cloudinary.com/dtt23w3fy/image/upload/v1711889846/cirskgbrnuapbssxbo9m.png",
            name:"Trợ lý ảo",
            id: 0
          },
          isChatbot: false,
          textBot:"",
      };
    },
    computed:{
    ...mapGetters(["getUser"]),
      isMessageEmpty() {
        return !this.message.trim(); 
      }
    },
    mounted(){    
       this.getListIdUserChat();
    },
    methods: {
      async loadListAdviser(){
        try {
          if(this.getUser != null && this.getUser.userRole !=='ROLE_USER'){
            const response = await authApi().post(endpoints["ListUserChat"],this.lstIdUser);
            this.lstAdviser = response.data;
          }else{
            const response = await Apis.get(endpoints["ListAdviser"]);
            this.lstAdviser = response.data;
          }
        } catch (error) {
          console.error("Error fetching data:", error);
        }
      },
      sendMessage(){
        if(this.isChatbot == true){
          this.groupChat=String(this.getUser.id)+"+0";
          push(ref(database, this.groupChat),{
            id: this.getUser.id,
            refId: 12,
            avatar: this.getUser.avatar,
            username: this.getUser.name,
            content: this.message
          })
          this.sendMessageToDialogflow();
          console.log(this.textBot)
          setTimeout(() => {
          if (this.textBot !== null) {
              push(ref(database, this.groupChat), {
                id: this.bot.id,
                refId: 12,
                avatar: this.bot.avatar,
                username: this.bot.name,
                content: this.textBot
              });
            } else {
              console.log('sendMessageToDialogflow() trả về null.');
            }
          }, 3000);
          this.message ="";
          this.$refs['scrollableNew'].scrollIntoView({ behavior: 'smooth' });
          return;
        }else{
          if(this.adviser != null && this.getUser != null){
            if(this.getUser.userRole === 'ROLE_USER'){
              this.groupChat=String(this.getUser.id)+"+"+String(this.adviser.id)
            }else{
              this.groupChat=String(this.adviser.id)+"+"+String(this.getUser.id)
            }
          }
        }
        push(ref(database, this.groupChat),{
          id: this.getUser.id,
          refId: 12,
          avatar: this.getUser.avatar,
          username: this.getUser.name,
          content: this.message
        })
        this.message="";
        this.$refs['scrollableNew'].scrollIntoView({ behavior: 'smooth' });
      },
      async sendMessageToDialogflow() {
        try {
            // Dữ liệu yêu cầu gồm text của người dùng
            const requestData = {
                queryInput: {
                    text: {
                        text: this.message,
                        languageCode: "vi"
                    }
                }
            };

            // Gửi yêu cầu POST đến URL webhook của Dialogflow
            const response = await axios.post('https://console.dialogflow.com/v1/integrations/messenger/webhook/e24946cc-4a40-4ae6-9107-5eb7892a00aa/sessions/webdemo-79b99670-9acd-ae20-f97c-1437c867da4e?platform=webdemo', requestData);

            // Lưu trữ phản hồi từ Dialogflow vào biến response
            const startText = '"fulfillmentText": "';
            const endText = '",';

            const startIndex = response.data.indexOf(startText) + startText.length;
            const endIndex = response.data.indexOf(endText, startIndex);
            // Trích xuất phần của chuỗi JSON chứa nội dung "fulfillmentText" và trả về
            return this.textBot = await response.data.substring(startIndex, endIndex);
        } catch (error) {
            // Xử lý lỗi nếu có
            console.error('Error:', error);
            return ""; // Trả về giá trị mặc định nếu có lỗi
        }
    },

      
      async getMessage(){
        if(this.adviser != null && this.getUser != null){
          if(this.getUser.userRole === 'ROLE_USER'){
            this.groupChat=String(this.getUser.id)+"+"+String(this.adviser.id)
          }else if(this.isChatbot == true){
            this.groupChat=String(this.getUser.id)+"+0"
          }else{
            this.groupChat=String(this.adviser.id)+"+"+String(this.getUser.id)
          }
        }
        await onValue(ref(database, this.groupChat), (data) => {
            this.messages=data.val();
        })
        this.$refs['scrollableNew'].scrollIntoView({ behavior: 'smooth' });
      },
      sentOrReceived(userID) {
        return userID === this.getUser.id ? 'sent' : 'received'
      },
      handleAdviserClick(adviser) {
        this.isChatbot =false;
        this.adviser=adviser;
        this.getMessage();
      },
      handleChatBotClick() {
        this.isChatbot = true
        this.adviser=this.bot;
        this.getMessage();
      },
      getListIdUserChat(){
        try {
          if(this.getUser != null  ){
          onValue(ref(database), (data) => {
            const tables = data.val();
            for (const key in tables) {
              if(parseInt(key.slice(key.indexOf('+'))) ==this.getUser.id ){
                this.lstIdUser.push(parseInt(key.slice(key.indexOf('+')-10)));
              }
            }
            this.loadListAdviser();
            })
          }
         
        } catch (error) {
          console.error("Error fetching data:", error);
        }
      },

    }
}
</script>

<style lang="scss">
.bg-new {
  background-color: #74EBD5;
  background-image: linear-gradient(90deg, #74EBD5 0%, #9FACE6 100%);

  min-height: 100vh;
}

::-webkit-scrollbar {
  width: 5px;
}

::-webkit-scrollbar-track {
  width: 5px;
  background: #f5f5f5;
}

::-webkit-scrollbar-thumb {
  width: 1em;
  background-color: #ddd;
  outline: 1px solid slategrey;
  border-radius: 1rem;
}

.text-small {
  font-size: 0.9rem;
}

.messages-box,
.chat-box {
  height: 490px;
  overflow-y: scroll;
}

.rounded-lg {
  border-radius: 0.5rem;
}

input::placeholder {
  font-size: 0.9rem;
  color: #999;
}
.message {
      display: flex;
      align-items: center;

      &.received {
        p {
          text-align: left;
          background: #e5e5ea;
          color: #000;
          max-width: 450px;
          word-wrap: break-word;
        }
      }

      &.sent {
        flex-direction: row-reverse;

        p {
          text-align: left;
          color: #fff;
          background: #0b93f6;
          align-self: flex-end;
          max-width: 450px;
          word-wrap: break-word;
        }
      }

      img {
        width: 40px;
        height: 40px;
        border-radius: 50%;
        margin: 2px 5px;
      }

      p {
        max-width: 500px;
        margin-bottom: 12px;
        line-height: 24px;
        padding: 10px 20px;
        border-radius: 25px;
        position: relative;
        color: #fff;
        text-align: center;
      }
    }
.list-group-item.active{
  border: none !important;
  background-color: white !important;
  color: gray !important;
}
.list-group-item.active:hover{
  border: none !important;
  background-color: lightgray !important;
}
.list-group-item.active:focus{
  border: none !important;
  background-color: red !important;
}
</style>