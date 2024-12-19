<%-- 
    Document   : view_hakmilik_popup
    Created on : Dec 8, 2014, 12:08:36 PM
    Author     : fikri
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<link type="text/css" rel="stylesheet"
      href="${pageContext.request.contextPath}/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="${pageContext.request.contextPath}/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="${pageContext.request.contextPath}/pub/styles/tablecloth.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<script language="javascript">
    $(document).ready(function(){
        $("#tukar").hide();
    });

    function selectAll(a){
        var bil = ${fn:length(actionBean.senaraiHakmilik)};
        var str1 = '';
        for (i = 0; i < bil; i++){
            var c = document.getElementById("hakmilik" + i);
            if(c.value == 0){
                c.checked = a.checked;
                var str = selecHakmilik(a,str);
                if(i == (bil-1)){
                    str1 = str1+str;
                }else{
                    str1 = str+","+str1;}
            }
        }
//        $("#str").val(str1);
    }
    
    function selecHakmilik(a, str){
        var c = document.getElementById("listHakmilik" + i);
        c.checked = a.checked;
        $("#tukar").show();
        return c.value;
    }
    
    function selectOne(a, bil){
        var c = document.getElementById("listHakmilik" + bil);
        var str = document.getElementById("str");
        var str1 = '';
        if(c.checked){
            $("#tukar").show();
        }else{
        }
        if(str.value == ' '){
            str1 = c.value;
//            $("#str").val(str1);
        }else{
            str1 = str.value+','+c.value;
//            $("#str").val(str1);
        }
    }
    
    function selectList(){
        $("#str").val('');
        var bil = ${fn:length(actionBean.senaraiHakmilik)};
        var str1 = '';
        for (i = 0; i < bil; i++){
            var c1 = document.getElementById("listHakmilik" + i);
            if(c1.checked){
                var str = c1.value;
                if(str1 == ''){
                    str1 = str;
                }else{
                    str1 = str1+","+str;}
                    
            }                
        }
        $("#str").val(str1);
    }
    
    function close1(f){
        if(confirm('Adakah anda pasti untuk meneruskan proses tkar ganti?')){
            selectList();
            var c = document.getElementById("str");
            self.opener.refreshRekod1(c.value);
            self.close() ;
            return false;
        }else
            return false;
    }
</script>
<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<s:form id="popup" beanclass="etanah.view.stripes.SenaraiTugasanAction" name="form1">
    <s:text name=" " id="str" value=" " size="50" style="display:none;"/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Senarai Hakmilik</legend>

            <div class="content" align="center">
                <display:table class="tablecloth" name="${actionBean.senaraiHakmilik}"
                               id="line" style="width:95%"
                               requestURI="senaraiTugasan"
                               requestURIcontext="true"
                               sort="external" size="${actionBean.senaraiHakmilik}">     
                    <display:column title="Bil">      
                        ${line_rowNum}
                    </display:column>
                    <display:column title="ID Hakmilik" >
                        <c:if test="${line.hakmilik ne null}">${line.hakmilik.idHakmilik}</c:if>
                        <c:if test="${line.hakmilik eq null}">-</c:if>
                    </display:column>
                    <c:choose>
                        <c:when test="${line.hakmilik ne null && line.hakmilik.bandarPekanMukim ne null}">
                            <display:column property="hakmilik.bandarPekanMukim.nama" title="BandarPekanMukim"  />                         
                        </c:when>
                        <c:when test="${line.bandarPekanMukimBaru ne null}">
                             <display:column property="bandarPekanMukimBaru.nama" title="BandarPekanMukim"  />    
                        </c:when>
                        <c:otherwise>
                                   -             
                        </c:otherwise>
                    </c:choose>
                    <%--display:column title="<input type='checkbox' id='semua' name='semua' 
                                    title='Sila klik untuk tukar ganti' onclick='javascript:selectAll(this);' /> 
                                    Tukar Ganti">
                        <div align="center">
                        <c:choose>
                            <c:when test="${line.hakmilik.noVersiDhde eq 0 }">
                                <s:checkbox name="" style="display:none;"
                                          value="${line.hakmilik.noVersiDhde}" id="hakmilik${line_rowNum - 1}"/>
                                <s:checkbox name="listHakmilik[${line_rowNum - 1}].idHakmilik"
                                            value="${line.hakmilik.idHakmilik}" id="listHakmilik${line_rowNum - 1}" onclick='javascript:selectOne(this,${line_rowNum-1});'/>
                            </c:when>
                            <c:otherwise>
                                   <s:checkbox name="" style="display:none;"
                                          value="${line.hakmilik.noVersiDhde}" id="hakmilik${line_rowNum - 1}"/>Hakmilik telah ditukar ganti.             
                            </c:otherwise>
                        </c:choose>
                        </div>
                    </display:column--%>
                </display:table><br>
                <s:submit name="" value="Tukar Ganti" class="btn" id="tukar" onclick="return close1(this.value);"/>
            </div>

        </fieldset>
    </div>

</s:form>
