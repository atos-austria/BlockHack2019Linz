
from Savoir import Savoir


class MultichainCommitter(Savoir):

    def __init__(self, rpcuser, rpcpasswd, rpchost, rpcport, chainname):
        self.rpcuser = rpcuser
        self.rpcpasswd = rpcpasswd
        self.rpchost = rpchost
        self.rpcport = rpcport
        self.chainname = chainname
        try:
            super().__init__(rpcuser, rpcpasswd, rpchost, rpcport, chainname)
            self.getinfo()
        except Exception as ex:
            print(ex)

    def commit(self, key, data, stream="root"):
        return self.publish(stream, key, str(data).encode().hex())

    def run_cmd_input(self):
        try:
            while True:
                line = input("Input data: ")
                print(self.commit('cmd_line_input_py', line))
        except KeyboardInterrupt:
            print('shutting down.')

if __name__ == '__main__':

    bc = MultichainCommitter('****', '****', '***.***.***.***', '****', '****')
    print(bc.getinfo())

    bc.run_cmd_input()