<%-- 
    Document   : kemasukanPermohonanLaluAwam
    Created on : 18-Oct-2010, 11:42:00
    Author     : nordiyana
--%>

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/scripts/tablecloth.js"></script>
<script type="text/javascript">
function removeSingle(id)
{
if(confirm('Adakah anda pasti?')) {
var url = '${pageContext.request.contextPath}/pengambilan/rekodTuntut?deleteSingle&id='
+id;
$.get(url,
function(data){
$('#page_div').html(data);
},'html');}
       <%-- alert(ialertd);--%>
}
     function tambahBaru(){
            window.open("${pageContext.request.contextPath}/pengambilan/rekodTuntut?hakMilikPopup", "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=910,height=800");
        }

function refreshPageHakmilik(){
var url = '${pageContext.request.contextPath}/pengambilan/rekodTuntut?refreshpage';
$.get(url,
function(data){
$('#page_div').html(data);
},'html');
}

  $(document).ready( function() {


        var len = $(".daerah").length;

        for (var i=0; i<=len; i++){
            $('.hakmilik'+i).click( function() {
                window.open("${pageContext.request.contextPath}/pengambilan/rekodTuntut?popup&idHakmilik="+$(this).text(), "eTanah",
                    "status=0,toolbar=0,location=0,menubar=0,width=890,height=600");
            });
        }
    });

  function validateLuas(idVar,rowNo){

     var str = idVar.value;
     var luasTerlibat = parseInt(idVar.value);
     var luas = parseInt(document.getElementById("luas"+rowNo).value);

     if(luasTerlibat > luas){
         alert("Luas Diambil must less than Luas");
         idVar.value = str.substring(0,str.length-1);
         idVar.focus();
         return true;
      }
  }

  function validateKodUnitLuas(idVar,rowNo){
      <%--alert(idVar.value);--%>
      if(idVar.value == 'M'){
          var unitLuasDiambil = "Meter Persegi";
          alert(unitLuasDiambil);
      }
      if(idVar.value == 'H'){
          var unitLuasDiambil = "Hektar";
          alert(unitLuasDiambil);
      }

      var unitLuas = document.getElementById("unitLuas"+rowNo).value;
      alert(unitLuas);

  }

  function save(event, f){
        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.post(url,q,
        function(data){
            $('#page_div',opener.document).html(data);

        },'html');

    }
</script>

<s:form beanclass="etanah.view.stripes.pengambilan.rekodTuntutanLebihanActionBean">
    <s:messages/>
    <div class="subtitle displaytag">

        <fieldset class="aras1">
            <legend>
                Butiran Permohonan 
            </legend>
            <p align="center">
                <c:if test="${edit}">

                <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" pagesize="20" cellpadding="0" cellspacing="0"
                               requestURI="/pengambilan/maklumat_asas_pengambilan" id="line">
                    <display:column title="No" sortable="true">${line_rowNum}</display:column>
                    <display:column property="hakmilik.idHakmilik" title="ID Hakmilik" class="popup hakmilik${line_rowNum}"/>
                    <display:column title="Luas Diperlukan">
                        <s:text name="luasTerlibat[${line_rowNum - 1}]" id="luasTerlibat${line_rowNum - 1}"  onkeyup="validateLuas(this,${line_rowNum - 1})"/>
                        &nbsp;${line.hakmilik.kodUnitLuas.nama}
                    </display:column>
                    <display:column title="tujuan" />
                    <display:column title="Pihak Berkepentingan">
                         <c:set value="1" var="count"/>
                     <c:forEach items="${line.hakmilik.senaraiPihakBerkepentingan}" var="senarai">
                         <c:if test="${senarai.jenis.kod ne 'PM'}">
                             <br>
                             <c:out value="${count}"/>)&nbsp;
                                <c:out value="${senarai.pihak.nama}"/>&nbsp;&nbsp;
                                <c:set value="${count + 1}" var="count"/><br>
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;No.KP/Co:<c:out value="${senarai.pihak.noPengenalan}"/>
                           </c:if>
                       </c:forEach>
                   </display:column>
                           <display:column title="Lokasi"/>
                </display:table>
                    <br>
                    <s:button name="simpanHakmilik" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>

            </c:if>
            <c:if test="${!edit}">
                 <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" pagesize="20" cellpadding="0" cellspacing="0"
                               requestURI="/pengambilan/maklumat_asas_pengambilan" id="line">
                    <display:column title="No" sortable="true">${line_rowNum}</display:column>
                    <display:column property="hakmilik.idHakmilik" title="ID Hakmilik" class="popup hakmilik${line_rowNum}"/>
                    <display:column title="Luas Diperlukan" property="luasTerlibat"><%--&nbsp;${line.hakmilik.kodUnitLuas.nama}--%></display:column>
                     <display:column title="tujuan" />
                    <display:column title="Pihak Berkepentingan">
                      <c:set value="1" var="count"/>
                     <c:forEach items="${line.hakmilik.senaraiPihakBerkepentingan}" var="senarai">
                         <c:if test="${senarai.jenis.kod ne 'PM'}">
                             <br>
                             <c:out value="${count}"/>)&nbsp;
                                <c:out value="${senarai.pihak.nama}"/>&nbsp;&nbsp;
                                <c:set value="${count + 1}" var="count"/><br>
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;No.KP/Co:<c:out value="${senarai.pihak.noPengenalan}"/>
                                 </c:if>
                     </c:forEach>
                    </display:column>
                  <display:column title="lokasi" />
                </display:table>
            </c:if>
                <br>
        </fieldset>
    </div>

</s:form>