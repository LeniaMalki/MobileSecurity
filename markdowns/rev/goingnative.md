# Solution

## Description of the problem 

## Solution

I decompiled the code with Ghidra. By doing this, we can see the several condition of the FlagChecker function.
The `if ((((sVar2 == 12) && (param_4 == 31337)) && (*__s == 'n')) && (__s[11] == 'o'))` gives us the initial conditions.
Next, `iVar1 = strncmp("ative",local_2e,5);` checks whether the 5 fist characters of the string at local_2e is equal to "ative".
Here, `if (((iVar1 == 0) && (__s[9] == '_')) && ((__s[6] == '_' && ((__s[7] == 'i' && (__s[8] == 's'))))))`, we obtain a coiole
more characters. It is quite straightforward. And lastly, we check weather the last 2 letters of our string is equal to "so" through
`iVar1 = strcmp("so",__s + 10);`. The first part of the flag is given by the __s string which gives us "native_is_so".
By combining this with the second part of the flag, which is 31337. However, we can see in the Jaca code that the length has
to be of size 6. A 0 has been removed during decompilation which we have to add back. 

```c
ulong Java_com_mobisec_gonative_FlagChecker_helloFromTheOtherSide
                (long *param_1,undefined8 param_2,undefined8 param_3,int param_4)

{
  int iVar1;
  char *__s;
  size_t sVar2;
  ulong uVar3;
  long in_FS_OFFSET;
  undefined auStack_100 [192];
  undefined *puStack_40;
  char local_2e [5];
  undefined local_29;
  long local_28;
  
  local_28 = *(long *)(in_FS_OFFSET + 0x28);
  puStack_40 = (undefined *)0x1007be;
  __s = (char *)(**(code **)(*param_1 + 0x548))(param_1,param_3,0);
  puStack_40 = (undefined *)0x1007c9;
  sVar2 = strlen(__s);
  if ((((sVar2 == 12) && (param_4 == 31337)) && (*__s == 'n')) && (__s[11] == 'o')) {
    puStack_40 = (undefined *)0x100806;
    strncpy(local_2e,__s + 1,5);
    local_29 = 0;
    puStack_40 = (undefined *)0x10081f;
    iVar1 = strncmp("ative",local_2e,5);
    if (((iVar1 == 0) && (__s[9] == '_')) &&
       ((__s[6] == '_' && ((__s[7] == 'i' && (__s[8] == 's')))))) {
      puStack_40 = (undefined *)0x100852;
      iVar1 = strcmp("so",__s + 10);
      uVar3 = (ulong)auStack_100 | (ulong)(iVar1 == 0);
      puStack_40 = (undefined *)0x10086a;
      (**(code **)(*param_1 + 0x550))(param_1,param_3,__s);
      goto LAB_00100880;
    }
  }
  puStack_40 = (undefined *)0x10087e;
  (**(code **)(*param_1 + 0x550))(param_1,param_3,__s);
  uVar3 = 0;
LAB_00100880:
  if (*(long *)(in_FS_OFFSET + 0x28) == local_28) {
    return uVar3 & 0xffffffff;
  }
                    /* WARNING: Subroutine does not return */
  puStack_40 = &UNK_001008a2;
  __stack_chk_fail();
}
```
