
<%--
    Document   : ListKod
    Created on : Feb 1, 2011, 3:36:14 PM
    Author     : afham
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Utiliti Kod</title>
        <link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
        <link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
        <link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
        <script type="text/javascript"
        src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
        <script type="text/javascript"
        src="<%= request.getContextPath()%>/pub/scripts/ui.datepicker.js"></script>
        <link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
        <link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
        <script type="text/javascript"
        src="<%= request.getContextPath()%>/pub/scripts/ui.core.js"></script>
        <link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/pub/css/formdesign.css">
        <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
        <script src="<%= request.getContextPath()%>/pub/js/ui.datepicker-ms.js" type="text/javascript"></script>
        <script type="text/javascript">


            function tutup(){
                self.close() ;
                opener.confirmRefresh() ;
            }
            function validate(){
                if($("#kod").val() == ""){
                    alert("Sila masukkan Kod") ;
                    return false ;
                }
                if($("#nama").val() == ""){
                    alert("Sila masukkan Nama");
                    return false ;
                }
                if($("#aktif").val() == ""){
                    alert("Sila masukkan Kod Aktif");
                    return false ;
                }
                if($("#diMasukOleh").val() == ""){
                    alert("Sila masukkan Id Pengguna") ;
                    return false ;
                }
                if($("#tarikhMasuk").val() == ""){
                    alert("Sila masukkan Tarikh") ;
                    return false ;
                }
                return true ;
            }
            
            function testing(){
       
                var nameTable= $("#nameTable").val();
            <%--
                            var kod = $("#kod").val();
                            var nama = $("#nama").val();
                            var aktif = $("#aktif").val();
                            var diMasukOleh = $("#diMasukOleh").val();
                            var tarikhMasuk = $("#tarikhMasuk").val();

                var item = new Array();
                item[0] = "'"+kod+"'";
                item[1] = "'"+nama+"'";
                item[2] = "'"+aktif+"'";
                item[3] = "'"+diMasukOleh+"'";
                item[4] = "'"+tarikhMasuk+"'";

                alert(item);--%>

                        if(confirm('Adakah anda pasti?')) {
            <%--var url = '${pageContext.request.contextPath}/utility/kodlist?addData&item='+item+'&tableName='+nameTable;--%>
                        var url = '${pageContext.request.contextPath}/utility/kodlist?addData';
                        $.get(url,
                        function(data){
                            $('#page_div').html(data);
            <%-- self.close();
            --%><%--opener.confirmRefresh();--%>
                                                       
                        },'html');
            <c:if test="${flag eq true}">
                        opener.confirmRefresh();
                        self.close();
            </c:if>

                    }
        
       
        
                }

                function saveBetul2(){
                    var size = $("#money").val() ;
                    var name = $("#nameTable").val();
                    var item = new Array();


                    var a;
                    a = $("#data"+1).val() ;

                    for(var i=1;i<=size;i++){
                        a = $("#data"+i).val() ;
                        item[i-1] = "'"+a+"'";

                    }
          
                    if(confirm('Adakah anda pasti?')) {
             
                        var url = '${pageContext.request.contextPath}/utility/kodlist?addData&item='+item+'&tableName='+name;
                        $.get(url,
                        function(data){
                            $('#page_div').html(data);
                        },'html');      
                    }       
                }
 

                function test(){
                    var size = $("#money").val() ;
                    var item = new Array();

                    var total = null ;
                    var a;
                    a = $("#data"+1).val() ;
                    total = a;
                    for(var i=1;i<=size;i++){
                        a = $("#data"+i).val() ;
                        total = total + ";" + a ;
                        item[i-1] = "'"+a+"'";

                    }
          
                }
        </script>
    <body>
        <s:form beanclass="etanah.view.utility.ListKodUtil">
            <s:messages/>
            <s:errors/>
            <div class="subtitle">


                <fieldset class="aras1">
                    <s:hidden name="nameTable" id="nameTable"/>

                    <s:hidden name="${actionBean.columns}" id="money" value="${actionBean.columns}"/>
                    <legend>
                        Tambah Kod
                    </legend>                  

                    <div align="center">
                        <table class="tablecloth">
                            <tr>
                                <td>Kod :</td>
                                <td><s:text name="kod" id="kod" onblur="this.value=this.value.toUpperCase();"/></td>
                            </tr>
                            <tr>
                                <td>Nama :</td>
                                <td><s:text name="nama" id="nama" size="30"/></td>
                            </tr>
                            <tr>
                                <td> Aktif :</td>
                                <td><s:select name="aktif" id="aktif">
                                        <s:option value="">Sila Pilih</s:option>
                                        <s:option value="Y">Y</s:option>
                                        <s:option value="T">T</s:option>
                                    </s:select></td>
                            </tr>
                            <tr>
                                <td>Dimasukkan Oleh :</td>
                                <td><s:text name="diMasukOleh" id="diMasukOleh" disabled="true"/></td>
                            </tr>                           
                        </table>

                    </div>



                    <center>
                        <%--<s:button name="addData" id="simpan" value="Simpan" class="btn" onclick="if(validate())testing();"/>--%>
                        <s:submit name="addData" id="simpan1" value="Simpan" class="btn" onclick="testing();"/>
                        <s:button name="ttp" value="Tutup" class="btn" onclick="tutup();"/>

                    </center>

                    <br>
                    <br>

                </fieldset>
            </div>
        </s:form>
    </body>
</html>