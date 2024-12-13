<%-- 
    Document   : jenis_suratkuasa
    Created on : Dec 15, 2009, 5:27:12 PM
    Author     : mohd.fairul
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/eTanahSkin.css"/>

<script type="text/javascript">
  function saveTentuHakmilik() {
    alert("Test");
    var url = "${pageContext.request.contextPath}/daftar/tentukanHakmilik?saveTentukanHakmilik";
    $.post(url);
  }

  function deleteTentukan(idRuj, idHak) {
  <%--alert("ID RUJUKAN" + idRuj +"ID Hakmilik" + idHak);--%>
      var padam = window.confirm("Mansuhkan Rekod No. Rujukan Surat:" + idRuj + " Hakmilik:" + idHak);
      if (padam) {
        //start
        var url = '${pageContext.request.contextPath}/daftar/tentukanHakmilik?deleteTentukan&idRujDelete='
                + idRuj + '&idHakDelete=' + idHak;
        $.get(url,
                function(data) {
                  $('#page_div').html(data);
                  //$('#alert').html(' <div id="alert" style="color: red; font-size: 18pt; font-weight: bold" align="center">Rekod telah dimansuhkan..</div>').hide(5000);
  <%--setTimeout ( "hideMsg()", 2000 );--%>
                }, 'html');
        //end
      } else {
      }
    }
</script>

<s:form name="form1" beanclass="etanah.view.daftar.tentukanHakmilik">
  <s:messages />
  <s:errors />
  <div class="subtitle"> 
    <fieldset class="aras1">
      <legend>
        Kemasukan Berkaitan Dengan Hakmilik
      </legend>
      <div class="content" align="center">
        <display:table class="tablecloth" name="${actionBean.dokumen}" 
                       cellpadding="0" cellspacing="0" id="line">
          <display:column title="No" sortable="true">${line_rowNum}</display:column>
          <c:if test="${actionBean.kodNegeri eq '04'}">
          <display:column title="No Rujukan Surat" sortable="true" >
            <a href="#" onclick="window.open('${pageContext.request.contextPath}/daftar/pertanyaanSBSWSA?paparOnly2&idMohon=${line.noRujukan}', 'popup', 'width=1000,height=600,scrollbars=yes,resizable=yes,toolbar=no,directories=no,location=no,menubar=no,status=no,left=0,top=0');">
              <input onmouseover="this.style.cursor = 'pointer'" name="noRujukan[${line_rowNum-1}]" 
                     id="noRujukan${line_rowNum-1}" value="${line.noRujukan}" >
            </a>
          </display:column>
          </c:if>
          <c:if test="${actionBean.kodNegeri eq '05'}">
              <display:column title="No Rujukan Surat" sortable="true" >
                <a href="#" onclick="window.open('${pageContext.request.contextPath}/daftar/pertanyaanSBSWSA?paparOnly2&idMohon=${line.noRujukan}', 'popup', 'width=1000,height=600,scrollbars=yes,resizable=yes,toolbar=no,directories=no,location=no,menubar=no,status=no,left=0,top=0');">
                <input onmouseover="this.style.cursor = 'pointer'" name="noRujukan[${line_rowNum-1}]" 
                       id="noRujukan${line_rowNum-1}" value="${line.noRujukan}" readonly>
                </a>
          </display:column>
          </c:if>
          <c:if test="${fn:length(actionBean.permohonan.senaraiHakmilik) > 0}">   
          <display:column title="Hakmilik" >
            <%--<s:select  style="text-transform:uppercase" id="idHakmilikSW${line_rowNum}" name="idHakmilikSW[${line_rowNum-2}]" >--%>
            <s:select  style="text-transform:uppercase" id="idHakmilikSW${line_rowNum-1}" name="idHakmilikSW" >
              <s:options-collection collection="${actionBean.permohonan.senaraiHakmilik}" 
                                    label="hakmilik.idHakmilik" value="hakmilik.idHakmilik" />
            </s:select>
          </display:column>
          </c:if> 
          <c:if test="${fn:length(actionBean.permohonan.senaraiHakmilik) == 0}">   
          <display:column title="Hakmilik" >
            <%--<s:select  style="text-transform:uppercase" id="idHakmilikSW${line_rowNum}" name="idHakmilikSW[${line_rowNum-2}]" >--%>
            TIADA 
         </display:column>
          </c:if> 
        </display:table>

        <br/>
        <p id="buttonSimpan">
          <c:if test="${fn:length(actionBean.permohonan.senaraiHakmilik) > 0}">            
          <s:button class="longbtn" value="Simpan" name=" saveTentukanHakmilik" onclick="doSubmit(this.form, this.name, 'page_div')"/>
          </c:if> 
          &nbsp;
        </p>
      </div>
    </fieldset>
    <br/>

    <c:if test="${fn:length(actionBean.pdl) > 0 }">
      <fieldset class="aras1">
        <legend>
          Paparan
        </legend>
        <div class="content" align="center">
          <!--<div id="alert" style="color: red; font-size: 18pt; font-weight: bold" align="center"></div>-->
          <display:table class="tablecloth" name="${actionBean.pdl}" 
                         cellpadding="0" cellspacing="0" id="line">
            <display:column title="No" sortable="true">${line_rowNum}</display:column>
            <display:column title="No Rujukan Surat" sortable="true" >
              ${line.noRujukan} 
            </display:column>
            <display:column title="Untuk Kegunaan Hakmilik" sortable="true" >
              ${line.hakmilikPermohonan.hakmilik.idHakmilik}
            </display:column>
            <display:column title="Mansuh">
              <div  align="center">
                <img alt='Klik Untuk Mansuh' title="Klik Untuk Mansuh" border='0' src='${pageContext.request.contextPath}/pub/images/not_ok.gif' class='rem'id='hjhgh' onmouseover="this.style.cursor = 'pointer';" onclick="deleteTentukan('${line.noRujukan}', '${line.hakmilikPermohonan.hakmilik.idHakmilik}')"/>
              </div>
            </display:column>
          </display:table>
        </div>
        <br/>
      </fieldset>
    </c:if>
<%-- pn safina suruh tutup pd 21/08/2013
    <c:if test="${actionBean.flagSB != null}">
      <fieldset class="aras1">
        <legend>
          Maklumat Surat Kebenaran
        </legend>  
        <div class="content" align="center">
          <display:table class="tablecloth" name="${actionBean.listSB}" cellpadding="0" cellspacing="0" id="lineSB">  
            <display:column title="No ">${lineSB_rowNum}</display:column>
            <display:column title="No. Rujukan Surat" property="id_wakil"/>
            <display:column title="Untuk Kegunaan Hakmilik" >
              <c:forEach items="${lineSB.id_hakmilik}" var="item">
                ${item.hakmilik.idHakmilik}<br/>                
              </c:forEach>             
            </display:column>            
            <display:column title="Kebenaran Pindahmilik" >
              <s:select name="kuasaPindahMilik[${listSB_rowNum}]" value="${lineSB.pindahmilik}" style="width:100px">           
                <s:option value="T"> Tidak </s:option>  
                <s:option value="Y"> Ya </s:option> 
              </s:select>
            </display:column>
            <display:column title="Kebenaran Gadaian" >
              <s:select name="kuasaGadai[${listSB_rowNum}]" value="${lineSB.Gadaian}" style="width:100px">           
                <s:option value="T"> Tidak </s:option>  
                <s:option value="Y"> Ya </s:option> 
              </s:select>
            </display:column>
            <display:column title="Kebenaran Pajakan" >
              <s:select name="kuasaPajak[${listSB_rowNum}]" value="${lineSB.Pajakan}" style="width:100px">           
                <s:option value="T"> Tidak </s:option>  
                <s:option value="Y"> Ya </s:option> 
              </s:select>
            </display:column>
          </display:table>        
          <br> 
          <p id="buttonSimpan">
            <s:button class="btn" value="Simpan" name="saveWakilKuasaSB" onclick="doSubmit(this.form, this.name, 'page_div')"/>
            &nbsp;
          </p>
        </div>
        <br>
      </fieldset>
    </c:if>
--%>
    <c:if test="${fn:length (actionBean.wKuasa) > 0 }">
      <fieldset class="aras1">
        <legend>
          Maklumat Pemberi dan Penerima
        </legend>
        <div class="content" align="center">
          <!--<div id="alert" style="color: red; font-size: 18pt; font-weight: bold" align="center"></div>-->
          <display:table class="tablecloth" name="${actionBean.wKuasa}" cellpadding="0" cellspacing="0" id="line">
            <%--<display:column title="No" sortable="true">${line_rowNum}</display:column>--%>
            <%--<c:if test="${!empty line.senaraiPemberi && !empty line.senaraiPenerima}">--%>
            <display:column title="No. Surat" ><br/>${line.idWakil}</display:column>
            <display:column title="Pemberi" sortable="true">
              <display:table class="tablecloth" name="${line.senaraiPemberi}" cellpadding="0" cellspacing="0" id="line2">
                <display:column title="Nama" sortable="true">${line2.nama}</display:column>
                <display:column title="Jenis Pengenalan" sortable="true">${line2.kodPengenalan}</display:column>
                <display:column title="No Pengenalan" sortable="true">${line2.noPengenalan}</display:column>
              </display:table>
            </display:column>
            <display:column title="Penerima" sortable="true">
              <display:table class="tablecloth" name="${line.senaraiPenerima}" cellpadding="0" cellspacing="0" id="line3">
                <display:column title="Nama" sortable="true">${line3.nama}</display:column>
                <display:column title="Jenis Pengenalan" sortable="true">${line3.jenisPengenalan.nama}</display:column>
                <display:column title="No Pengenalan" sortable="true">${line3.noPengenalan}</display:column>
              </display:table>
            </display:column>
            <%--</c:if>--%>
          </display:table>
        </div>
        <br/>
      </fieldset>
    </c:if>
  </div>
  <br>
</s:form>
