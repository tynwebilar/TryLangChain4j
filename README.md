# LangChain4j AI客服助手

一个基于Spring Boot和LangChain4j的智能AI客服对话系统，支持多轮对话记忆和Markdown格式显示。

本项目仍在持续更新，目的想把LangChain4j 绝大部分功能以demo形式展示出来

## 📸 项目截图

<div align="center">
  <img src="https://github.com/tynwebilar/TryLangChain4j/blob/master/src/main/resources/static/markdown%E6%A0%BC%E5%BC%8F%E6%98%BE%E7%A4%BA%E6%94%AF%E6%8C%81.png" alt="AI多轮对话界面" width="600" height="400" style="border-radius: 8px; box-shadow: 0 4px 8px rgba(0,0,0,0.1);">
</div>

## ✨ 功能特性

- 🤖 **智能对话**：基于LangChain4j的AI对话系统
- 💬 **多轮对话**：支持上下文记忆，保持对话连贯性
- 🧠 **记忆存储**：使用Redis存储对话历史，支持会话管理
- 🆕 **新建对话**：一键清空历史，开始新的对话
- 📝 **Markdown支持**：AI回复支持Markdown格式显示
- 🎨 **美观界面**：现代化的聊天界面设计
- 📱 **响应式设计**：适配不同屏幕尺寸

## 🛠️ 技术栈

### 后端
- **Spring Boot 3.x** - 主框架
- **LangChain4j** - AI对话框架
- **Redis** - 对话历史存储
- **Maven** - 依赖管理

### 前端
- **HTML5 + CSS3** - 页面结构和样式
- **JavaScript (ES6+)** - 交互逻辑
- **Marked.js** - Markdown解析
- **Fetch API** - 异步请求

## 🚀 快速开始

### 环境要求
- Java 17+
- Maven 3.6+
- Redis 6.0+

### 安装步骤

1. **克隆项目**
   ```bash
   git clone https://github.com/tynwebilar/TryLangChain4j.git
   cd TryLangChain4j
   ```

2. **配置Redis**
   - 确保Redis服务已启动
   - 默认连接配置：localhost:6379

3. **配置AI模型**
   - 在 `application.yml` 中配置你的AI模型API密钥

4. **启动应用**
   ```bash
   mvn spring-boot:run
   ```

5. **访问应用**
   - 打开浏览器访问：`http://localhost:8080`

## 📁 项目结构

```
src/
├── main/
│   ├── java/com/example/langchain4jtest/
│   │   ├── controller/
│   │   │   └── ChatController.java          # 聊天接口控制器
│   │   ├── service/
│   │   │   ├── ChatService.java             # 聊天业务逻辑
│   │   │   └── myChatMemoryStore.java       # Redis记忆存储
│   │   ├── config/
│   │   │   ├── DeepSeekApiConfig.java       # AI模型配置
│   │   │   └── RedisConfig.java             # Redis配置
│   │   └── Langchain4jTEstApplication.java  # 启动类
│   └── resources/
│       ├── static/
│       │   └── index.html                   # 前端页面
│       └── application.yml                  # 应用配置
```

## 🔧 主要功能

### 1. 智能对话
- 支持自然语言交互
- 基于上下文的多轮对话
- 智能回复生成

### 2. 对话管理
- 自动保存对话历史
- 支持新建对话功能
- 基于用户ID的会话隔离

### 3. 界面特性
- 实时打字指示器
- 支持回车发送消息
- 自动滚动到最新消息
- Markdown格式渲染

## 📝 API接口

### 发送消息
```http
POST /api/chat/send
Content-Type: application/json

{
  "message": "你好"
}
```

### 新建对话
```http
POST /api/chat/new
Content-Type: application/json

{}
```

### 健康检查
```http
GET /api/chat/health
```

## 🎯 使用示例

1. **开始对话**：在输入框中输入问题，点击发送或按回车
2. **查看回复**：AI会以Markdown格式显示回复内容
3. **新建对话**：点击"🆕 新建对话"按钮清空历史
4. **继续对话**：系统会记住之前的对话内容

## 🤝 贡献

欢迎提交Issue和Pull Request来改进这个项目！

## 🙏 致谢

- [LangChain4j](https://github.com/langchain4j/langchain4j) - 优秀的Java AI框架
- [Spring Boot](https://spring.io/projects/spring-boot) - 强大的Java应用框架
- [Marked.js](https://marked.js.org/) - Markdown解析库

---

⭐ 如果这个项目对你有帮助，请给它一个星标！

