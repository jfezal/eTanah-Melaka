<%-- 
    Document   : ListKod
    Created on : Feb 1, 2011, 3:36:14 PM
    Author     : afham
--%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ include file="/WEB-INF/jsp/include/taglibs.jspf" %>

<%--<link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
        <link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>--%>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>

<%--<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>--%>
<script type="text/javascript">
    function confirmRefresh() {
        var d = '${actionBean.nameTable}'
        var url = '${pageContext.request.contextPath}/utility/kodlist?pilihKod&nameTable='+d;
        $.get(url,
        function(data){
            $("#popupDiv").replaceWith($('#popupDiv', $(data)));
        }
        ,'html');
    }
    
    function refreshDocument(){
        document.location = document.location;
    }

    function testing(a){
             
        var url = '${pageContext.request.contextPath}/utility/kodlist?editData&no='+a;
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=900,height=700");
    }
    {

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
                $.ajax({
                    type:"GET",
                    url : url,
                    success : function(data) {
                        $('#page_div',opener.document).html(data);
                        self.close();
                    }
                });
            }

        }
        function tambah(){

            var url = '${pageContext.request.contextPath}/utility/kodlist?tambahData';
            window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=900,height=700");
        }
        function save(event, f){
            var q = $(f).formSerialize();
            var url = f.action + '?' + event;
            $.post(url,q,
            function(data){
                $('#page_div').html(data);
                opener.pilihKod();
            },'html');
        }
    }
</script>

<s:form beanclass="etanah.view.utility.ListKodUtil" >
    <s:messages/>
    <s:errors/>
    <div class="subtitle">
        <fieldset class="aras1">


            <div align="center">
                <c:if test="${actionBean.rowData eq false}">
                    <legend>Senarai Kod-kod dalam Pangkalan Data</legend>
                    <br>
                    <p>
                        <s:select name="nameTable" id="" value="nameTable" style="width:246px">
                            <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${actionBean.array}"/>
                        </s:select>
                    </p>
                    <p>
                        <s:submit name="pilihKod" value="Pilih" class="btn" onclick="if(validation());"/>
                        <%--<s:submit name="cariKod" value="Pilih" class="btn" onclick="if(validation());"/>--%>
                    </p>

                </c:if>
            </div>


            <c:if test="${actionBean.rowData eq true}">
                <s:hidden name="nameTable"/>

                <legend align="center">${actionBean.nameTable}</legend>
                <div align="center">
                    <p>
                        <s:button name="tambahData" id="tambahData" value="Tambah" class="btn" onclick="tambah();"/>
                        <s:submit name="showForm" value="Kembali" class="btn"/>                      
                    </p>         
                    <br><br>
                </div>

                <%--<c:set var="no" value="${actionBean.tempKod1}"/>--%>
                <c:set var="range" value="10"/>
                <div id="popupDiv">
                    <table cellpadding="0" cellspacing="0" align="center" class="tablecloth" datapagesize="20">                     
                        <tr>
                            <th>NO.</th>
                            <c:forEach items="${actionBean.columnNames}" var="i" begin="0" end="${actionBean.columns}">
                                <th>${i} &nbsp;</th>
                            </c:forEach>
                            <th>KEMASKINI</th>
                        </tr>

                        <c:forEach items="${actionBean.dataR}" var="c">

                            <tr>
                                <c:set var="no" value="${no+1}"/>
                                <td>${no} <s:hidden name="${no}" id="test"/></td>
                                <c:forEach items="${c}" var="b" >
                                    <td>${b}  &nbsp;</td>
                                </c:forEach>
                                <td>
                                    <p align="center">
                                        <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                             onclick="testing(${no});"  onmouseover="this.style.cursor='pointer';">
                                    </p>
                                </td>

                            </tr>

                        </c:forEach>
                    </table>
                </div>
            </c:if>           
        </fieldset>
    </div>
</s:form>