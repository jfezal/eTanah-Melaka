<%-- 
    Document   : maklumat_pemilik
    Created on : Apr 9, 2010, 4:22:14 PM
    Author     : khairil
--%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
  "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">
  function p(v, w) {
    $.blockUI({
      message: $('#displayBox'),
      css: {
        top: ($(window).height() - 50) / 2 + 'px',
        left: ($(window).width() - 50) / 2 + 'px',
        width: '50px'
      }
    });
    $.get("${pageContext.request.contextPath}/common/carian_hakmilik?popupPihak&idHakmilikPihakBerkepentingan=" + v + "&idHakmilik=" + w,
            function(data) {
              $("#perincianPihak").show();
              $("#perincianPihak").html(data);
              $.unblockUI();
            });
  }
  
  
  
  function viewPihakWaris(idHp){              
                window.open("${pageContext.request.contextPath}/daftar/pihak_kepentingan?PaparPihakWaris&idHp="+idHp, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=900,height=400");
  }
  
</script>
<div class="subtitle">
  <img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif" width="50" height="50" style="display: none"/>
  <fieldset class="aras1">
    <legend>Senarai Pemilik Yang Berkuatkuasa</legend>

    <div class="content" align="center" id="listpihak">
        <display:table class="tablecloth" style="width:90%;" name="${actionBean.hakmilikPihakKepentinganListAktif}" cellpadding="0" cellspacing="0" id="linemohonpihak">
            <display:column title="Bil" sortable="true">${linemohonpihak_rowNum}</display:column>
            <display:column title="Nama"><a href="#" onclick="p('${linemohonpihak.idHakmilikPihakBerkepentingan}', '${linemohonpihak.hakmilik.idHakmilik}');
    return false;">${fn:toUpperCase(linemohonpihak.nama)}</a></display:column>
            <display:column property="noPengenalan" title="Nombor Pengenalan" />
            <display:column property="noPengenalanLama" title="Nombor Pengenalan Lama" />
            <display:column title="Bahagian yang diterima">
                ${linemohonpihak.syerPembilang}
                /
                ${linemohonpihak.syerPenyebut}
            </display:column>
            <c:if test="${!empty actionBean.hakmilikPihakKepentinganListAktif}">  
                <c:if test="${linemohonpihak.jenis.kod ne 'PA' && linemohonpihak.jenis.kod ne 'PP'}">  
                    <display:column property='jenis.nama' title="Jenis Pihak"/>
                </c:if> 

                <c:if test="${linemohonpihak.jenis.kod eq 'PA' || linemohonpihak.jenis.kod eq 'PP'}">
                    <display:column   title="Jenis Pihak" >
                        <a href="#" onclick="viewPihakWaris(${linemohonpihak.idHakmilikPihakBerkepentingan});return false;">${linemohonpihak.jenis.nama}</a>
                    </display:column> 
                </c:if>

            </c:if>
            <%--add by m.fairul--%>
            <%--      <display:column title="Status">
                      <c:if test="${linemohonpihak.aktif eq 'Y'}">Berkuatkuasa</c:if>
                  </display:column>--%>
            <%--end add by m.fairul--%>
            <display:column title="ID Pihak" property="pihak.idPihak" class="hidden" headerClass="hidden" media="html"/>
        </display:table>
    </div>
    <br>
    <legend>Senarai Pemilik Yang Tidak Berkuatkuasa</legend>

    <div class="content" align="center" id="listpihak">
      <display:table class="tablecloth" style="width:90%;" name="${actionBean.hakmilikPihakKepentinganListTakAktif}" cellpadding="0" cellspacing="0" id="linemohonpihak">
        <display:column title="Bil" sortable="true">${linemohonpihak_rowNum}</display:column>
        <display:column title="Nama"><a href="#" onclick="p('${linemohonpihak.idHakmilikPihakBerkepentingan}', '${linemohonpihak.hakmilik.idHakmilik}');
    return false;">${fn:toUpperCase(linemohonpihak.nama)}</a></display:column>
        <display:column property="noPengenalan" title="Nombor Pengenalan" />
        <display:column property="noPengenalanLama" title="Nombor Pengenalan Lama" />
        <display:column title="Bahagian yang diterima">
          ${linemohonpihak.syerPembilang}
          /
          ${linemohonpihak.syerPenyebut}
        </display:column>
        <display:column property='jenis.nama' title="Jenis PB"/>
        <%--add by m.fairul--%>
        <%--<display:column title="Status">
            <c:if test="${linemohonpihak.aktif eq 'T'}">Tidak Berkuatkuasa</c:if>
        </display:column>--%>
        <%--end add by m.fairul--%>
        <display:column title="ID Pihak" property="pihak.idPihak" class="hidden" headerClass="hidden" media="html"/>
      </display:table>
      <br>
    </div>
  </fieldset>
  <br>
</div>
<div id="perincianPihak">
</div>