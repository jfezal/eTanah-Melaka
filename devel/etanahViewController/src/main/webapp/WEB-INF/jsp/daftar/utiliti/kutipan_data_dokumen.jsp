<%-- 
    Document   : kutipan_data_dokumen
    Created on : Nov 21, 2013, 3:40:01 PM
    Author     : azri
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://stripes.sourceforge.net/stripes-security.tld" prefix="ss"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<!DOCTYPE html>
<script type="text/javascript">
  $(document).ready(function() {
  });

  function doViewReport(v) {
    var url = '${pageContext.request.contextPath}/dokumen/view/' + v;
    window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800,scrollbars=yes");
  }

  function doPrintReport(v) {
//    var left = (screen.width / 2) - (w / 2);
//    var top = (screen.height / 2) - (h / 2);
    var url = '${pageContext.request.contextPath}/daftar/utiliti/kutipanData?cetak&id=' + v;
    window.open(url, "eTanah", "status=0,toolbar=0,location=no,menubar=0,scrollbars=0,resizable=0,width=300,height=100");
  }

  function doBlockUI() {
    $.blockUI({
      message: $('#displayBox'),
      css: {
        top: ($(window).height() - 50) / 2 + 'px',
        left: ($(window).width() - 50) / 2 + 'px',
        width: '50px'
      }
    });
  }
</script>

<div class="a">
  <s:errors/>
  <s:messages/>
  <s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
  <s:form beanclass="etanah.view.daftar.utiliti.KutipanDataActionBean">
    <img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif" width="50" height="50" style="display: none"/>
    <div class="subtitle">
      <fieldset class="aras1">
        <s:hidden name="kumpHm" id="kumpHm" value="${actionBean.kumpHm}"/> 
        <lagend>Dokumen Hakmilik</lagend>        
        <br>
        <font size="2" color="black">Petunjuk :</font>
        <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png" width="20" height="20" /> - 
        <font size="2" color="black">Papar Dokumen</font>&nbsp;<b>|</b>&nbsp;
        <img src="${pageContext.request.contextPath}/pub/images/icons/_active__print.png" width="20" height="20" /> - 
        <font size="2" color="black">Cetak Dokumen</font>
        <br>
        <br>
        <p align="center">
          <display:table class="tablecloth" style="width:90%;" id="line" cellpadding="0" cellspacing="0"
                         requestURI="/daftar/utiliti/kutipanData" name="${actionBean.listSenaraiHakmilik}" >
            <display:column title="Bil" style="width:1%;">
            <p align="right">${line_rowNum}</p>
          </display:column>
          <display:column title="ID Hakmilik">
            ${line.idHakmilik}
            <s:hidden name="hiddenIdHakmilik" id="hiddenIdHakmilik${line_rowNum-1}" value="${line.idHakmilik}"/>
          </display:column> 
          <display:column title="Versi Hakmilik" style="width:10%;">
            <div align="center">${line.noVersiDhde}</div>
          </display:column>
          <display:column title="Papar DHDE" style="width:10%;">
            <c:if test="${line.dhde ne null}">
              <c:if test="${line.dhde.namaFizikal ne null}">
                <p align="center">
                  <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png" width="20" height="20" title="Papar ${line.dhde.tajuk}"
                       onclick="doViewReport('${line.dhde.idDokumen}');" onmouseover="this.style.cursor = 'pointer';"/>
                </p>
              </c:if>
            </c:if>
          </display:column>
          <display:column title="Papar DHKE" style="width:10%;">
            <c:if test="${line.dhke ne null}">
              <c:if test="${line.dhke.namaFizikal ne null}">
                <p align="center">
                  <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png" width="20" height="20" title="Papar ${line.dhke.tajuk}"
                       onclick="doViewReport('${line.dhke.idDokumen}');" onmouseover="this.style.cursor = 'pointer';"/>
                </p>
              </c:if>
            </c:if>
          </display:column>
          <display:column title="Cetak DHDE" style="width:10%;">
            <c:if test="${line.dhde ne null}">
              <c:if test="${line.dhde.namaFizikal ne null}">
                <p align="center">
                  <img src="${pageContext.request.contextPath}/pub/images/icons/_active__print.png" title="Cetak ${line.dhde.tajuk}"
                       onclick="doPrintReport('${line.dhde.idDokumen}');" height="20" width="20" alt="cetak" onmouseover="this.style.cursor = 'pointer';"/>
                </p>
              </c:if>
            </c:if>
          </display:column>
          <display:column title="Cetak DHKE" style="width:10%;">
            <c:if test="${line.dhke ne null}">
              <c:if test="${line.dhke.namaFizikal ne null}">
                <p align="center">
                  <img src="${pageContext.request.contextPath}/pub/images/icons/_active__print.png" title="Cetak ${line.dhke.tajuk}"
                       onclick="doPrintReport('${line.dhke.idDokumen}');" height="20" width="20" alt="cetak" onmouseover="this.style.cursor = 'pointer';"/>
                </p>
              </c:if>
            </c:if>
          </display:column>
        </display:table>          
        </p>
        <br>
        <p align="center">
          <s:submit name="janaDokumen" id="janaDokumen" value="Jana Dokumen" class="longbtn"/>&nbsp;
        </p>
        <br>
      </fieldset>
    </div>
  </s:form>
</div>
