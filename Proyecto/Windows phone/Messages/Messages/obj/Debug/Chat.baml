   M S B A M L   `   `   `  ����C  ?Messages, Version=1.0.0.0, Culture=neutral, PublicKeyToken=null#clr-namespace:MessagesMessages  5   	       Messages.Chat   5   	   \ XPresentationFramework, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35R NWindowsBase, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35W SPresentationCore, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35D 9http://schemas.microsoft.com/winfx/2006/xaml/presentation    5   	   R NSystem.Xaml, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089:x,http://schemas.microsoft.com/winfx/2006/xaml     5   	   @d2http://schemas.microsoft.com/expression/blend/2008     5   	   Jmc;http://schemas.openxmlformats.org/markup-compatibility/2006     5   	   (localclr-namespace:Messages     5   	   -   5          �Name$  ChatForm��5   	    � Title$
 Chat��5   	   $	��350��6   $	��525��6#    � WindowStartupLocation$ CenterScreen=�5	   	   .��5
      �  � ColumnDefinitions 5   
   �� 5      $��9* �6   �� 5      $	��84* �6   �� 5      $	��51* �6   �� 5      $
��278* �6   �� 5      $	��81* �6   �� 5      $	��14* �6    � RowDefinitions 5   
   �� 5      $��* �6   �� 5      $��7* �6   �� 5      $��* �6   .+�5   
   A� $	��Top=�6    A� Columns$ 2��6/   $��0��6;   $��6��6H   .+�5      �� -   5        $��Stretch=�5   "   .��6@   Atras6G   o� 5      $��Right=�6   $
��Auto��6/   $
��Auto��6<   .@�5      m� $
��_...��6   $��Right=�6)   $��Stretch=�6E   
�������$
��Auto��6u   $
��Auto��6�   $��25(�6�   
���   �.@�5      m� -   5        $��
actualizar��5   6   $��_Actualizar��6H   
�������
���   �6p   m� 5      -   5        $��enviarArchivo��5   6   $��_Enviar archivo��6K   
�������
���   �6w   m� 5      -   5        $��verArchivos��5   6   $��_Ver archivos compartidos��6I   
�������
���   �6   5      5       5!      �� 5"   
   -   5        $��scroll��5"      $
��Auto=�6%   $��Disabled=�6H   $��1��6q   $��6��6~   .��5#      �� ��6   ��  �� Path$ messagesFrom���5$      �� 5%      .R�5&      A� $ 1��6&   $	��Top=�62   5'      5(      ��5*      �� 5+      .R�5,      A� $	��Top=�6C   ��6&   �� *color+��6[   �� *alineamiento+.+�5-      �� ��6$   �� *	message+6B   5.      5/      51      52      A� 53   
   $ 2��6   $��Bottom=�6"   $��2��6=   $��6��6J   .+�54      �� -   5        $
��text��54   4   $��Stretch=�6   6Z   �� 55      -   5        $��Stretch=�55      .��6A   Enviar6I   56      57      58      