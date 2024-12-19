<%--
    Document   : carian_gadaian
    Created on : Dec 3, 2009, 2:30:17 PM
    Author     : fikri
--%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
  "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">
  $(document).ready(function() {
    $('.checkbox').click(function() {
      $('#ids').val($('#ids').val() + ',' + $(this).val())
    });
  });

  function removeItem(val, idHakmilik, idUrusan) {   

        doBlockUI();
    var url = '${pageContext.request.contextPath}/daftar/nota/nota_daftar_batal?delete&id=' + val + '&idHakmilik=' + idHakmilik + '&idUrusan=' + idUrusan;   
    $.get(url,
            function(data) {
              $('#page_div').html(data);
              $('table.tablecloth thead a').each(function() {
                var url = $(this).attr('href');
                $(this).attr("href", "javascript:doSortPaging('" + url + "');");

              });
              $('.pagelinks a').each(function() {
                var url = $(this).attr('href');
                $(this).attr("href", "javascript:doSortPaging('" + url + "');");
              });
              $.unblockUI();
            }, 'html');
            
  }

  function doSortPaging(url) {
    var chkbox = "";
    $('.checkbox').each(function() {
      if ($(this).is(':checked')) {
        chkbox = chkbox + '&chkbox=' + $(this).val();
      }
    });
    url = url + chkbox + '&ids=' + $('#ids').val();

    $.get(url,
            function(data) {
              $('.displaytag').html(data);
              $('.popup').each(function() {
                $(this).html('<u>' + $(this).text() + '</u>');
              });
              $('table.tablecloth thead a').each(function() {
                var url = $(this).attr('href');
                $(this).attr("href", "javascript:doSortPaging('" + url + "');");

              });
              $('.pagelinks a').each(function() {
                var url = $(this).attr('href');
                $(this).attr("href", "javascript:doSortPaging('" + url + "');");
              });
            }
    , 'html');
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
<s:form beanclass="etanah.view.stripes.nota.NotaBatalActionBean">
  <s:hidden name="kodUrusan" id="kodUrusan" value="${actionBean.permohonan.kodUrusan.kod}"/>

  <div class="subtitle displaytag">
    <fieldset class="aras1">
      <legend>
        Senarai Urusan Terlibat
        <s:hidden name="ids" id="ids"/>
      </legend>
      <c:if test="${!(actionBean.permohonan.kodUrusan.kod eq 'PMDPT')}">   
        <div class="content" align="center">                
          <display:table class="tablecloth" name="${actionBean.hakmilikUrusanList}" pagesize="10" 
                         cellpadding="0" cellspacing="0" excludedParams="ids"
                         requestURI="${pageContext.request.contextPath}/daftar/nota/nota_daftar_batal" id="line">
            <display:column>
              <c:set var="checked"/>
              <c:forEach items="${actionBean.chkbox}" var="i">
                <c:if test="${i eq line.idUrusan}">
                  <c:set var="checked" value="checked"/>
                </c:if>
              </c:forEach>                        
              <s:checkbox name="chkbox" id="chkbox${line_rowNum}" checked="${checked}"
                          value="${line.idUrusan}" class="checkbox" />
            </display:column>
            <display:column title="No" sortable="true">${line_rowNum}</display:column>
            <display:column property="hakmilik.idHakmilik" title="ID Hakmilik"/>
            <display:column property="idPerserahan" title="ID Perserahan"/>
            <%--<display:column property="permohonan.idPermohonan" title="ID Perserahan"/>--%>
            <display:column property="kodUrusan.nama" title="Urusan"/>
            <display:column title="Tarikh Daftar">
                <fmt:formatDate value="${line.tarikhDaftar}" pattern="dd/MM/yyyy hh:mm:ss a"/>  
            </display:column>
          </display:table>
        </div>
      </c:if>
      <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PMDPT'}">
        <div class="content" align="center">                
          <display:table class="tablecloth" name="${actionBean.hakmilikMohonList}" pagesize="10" 
                         cellpadding="0" cellspacing="0" excludedParams="ids"
                         requestURI="${pageContext.request.contextPath}/daftar/nota/nota_daftar_batal" id="line">
            <display:column>
              <c:set var="checked"/>
              <c:forEach items="${actionBean.chkbox}" var="i">
                <c:if test="${i eq line.id}">
                  <c:set var="checked" value="checked"/>
                </c:if>
              </c:forEach>                        
              <s:checkbox name="chkbox" id="chkbox${line_rowNum}" checked="${checked}"
                          value="${line.id}" class="checkbox" />
            </display:column>
            <display:column title="No" sortable="true">${line_rowNum}</display:column>
            <display:column property="hakmilik.idHakmilik" title="ID Hakmilik"/>
            <display:column property="permohonan.idPermohonan" title="ID Perserahan"/>
            <display:column property="permohonan.kodUrusan.nama" title="Urusan"/>
            <%--<display:column property="permohonan.tarikhKeputusan" title="Tarikh Keputusan"/>--%>
            <display:column title="Tarikh Keputusan">
              <c:if test="${line.permohonan.tarikhKeputusan ne null}">
                <fmt:formatDate value="${line.permohonan.tarikhKeputusan}" pattern="dd/MM/yyyy hh:mm:ss a"/>                
              </c:if>
              <c:if test="${line.permohonan.tarikhKeputusan eq null}">
                <fmt:formatDate value="${line.permohonan.infoAudit.tarikhKemaskini}" pattern="dd/MM/yyyy hh:mm:ss a"/>                
              </c:if>
            </display:column>
          </display:table>
        </div>       
      </c:if>

      <c:if test="${fn:length(actionBean.hakmilikUrusanList) > 0 || fn:length(actionBean.hakmilikMohonList) > 0}">
        <div align="center">                                
          <s:button name="saveMelepasGadaian" id="add" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
        </div>
      </c:if>
      <br>
    </fieldset>

    <%--Edit to fit Nota Batal/lulus/--%>
    <fieldset class="aras1">
      <legend>Senarai Urusan Yang Disimpan</legend>
      <c:if test="${!(actionBean.permohonan.kodUrusan.kod eq 'PMDPT')}"> 
        <div class="content" align="center">
          <display:table class="tablecloth" name="${actionBean.senaraiMau}" pagesize="10" cellpadding="0" cellspacing="0"
                         requestURI="${pageContext.request.contextPath}/daftar/nota/nota_daftar_batal" id="line2">
            <display:column title="No" sortable="true">${line2_rowNum}</display:column>
            <display:column property="urusan.kodUrusan.nama" title="Urusan"/>
            <display:column property="urusan.hakmilik.idHakmilik" title="ID Hakmilik"/>
            <display:column property="urusan.idPerserahan" title="ID Perserahan"/>
            <display:column title="Hapus">
              <div align="center">
                <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                     id='rem_${line2_rowNum}' onclick="removeItem('${line2.idAtasUrusan}','${actionBean.hakmilik.idHakmilik}')" onmouseover="this.style.cursor = 'pointer';">
              </div>
            </display:column>
          </display:table>
        </div>
      </c:if>
      <c:if test="${(actionBean.permohonan.kodUrusan.kod eq 'PMDPT')}"> 
        <div class="content" align="center">
          <display:table class="tablecloth" name="${actionBean.senaraiMau}" pagesize="10" cellpadding="0" cellspacing="0"
                         requestURI="${pageContext.request.contextPath}/daftar/nota/nota_daftar_batal" id="line2">
            <display:column title="No" sortable="true">${line2_rowNum}</display:column>
            <display:column property="hakmilikPermohonan.permohonan.kodUrusan.nama" title="Urusan"/>
            <display:column property="hakmilikPermohonan.hakmilik.idHakmilik" title="ID Hakmilik"/>            
            <display:column title="Hapus">
              <div align="center">
                <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                     id='rem_${line2_rowNum}' onclick="removeItem('${line2.idAtasUrusan}' , '${actionBean.hakmilik.idHakmilik}' , '${line2.urusan.idUrusan}')" onmouseover="this.style.cursor = 'pointer';">
              </div>
            </display:column>
          </display:table>
        </div>
      </c:if>
      <br>
    </fieldset>
  </div>
</s:form>
