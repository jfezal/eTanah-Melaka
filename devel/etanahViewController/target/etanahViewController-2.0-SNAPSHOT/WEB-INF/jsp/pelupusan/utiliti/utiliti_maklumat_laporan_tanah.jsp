<%-- 
    Document   : carian_kelompok
    Created on : Apr 4, 2013, 10:09:54 PM
    Author     : afham
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<!DOCTYPE HTML PUBLIC "-//W3C//Dlabel HTML 4.01 pansitional//EN"
    "http://www.w3.org/p/html4/loose.dlabel">
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/scripts/tablecloth.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<script type="text/javascript">
    function openKelompok() {
        //        doBlockUI();
        window.open("${pageContext.request.contextPath}/pelupusan/permohonan_berkelompok?tambahKelompok", "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=1000,height=500,scrollbars=yes");
    }

    function openBertindih() {
        //        doBlockUI();
        window.open("${pageContext.request.contextPath}/pelupusan/permohonan_berkelompok?tambahBertindih", "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=1000,height=500,scrollbars=yes");
    }
    function editKelompok(idMohon, f) {
        doBlockUI();
        var q = $(f).serialize();
        var url = '${pageContext.request.contextPath}/pelupusan/permohonan_berkelompok?editKelompok&idMohon=' + idMohon;
        window.location = url + q;
        doUnBlockUI();
    }

    function editKelompok1(idMohon, frm) {
        var url = '${pageContext.request.contextPath}/pelupusan/permohonan_berkelompok?editKelompok&idMohon=' + idMohon;
        frm.action = url;
        frm.submit();
    }

    function deleteKelompok(idMohon, frm) {
        if (confirm("Adakah anda pasti?")) {
            var url = '${pageContext.request.contextPath}/pelupusan/permohonan_berkelompok?deleteKelompok&idMohon=' + idMohon;
            frm.action = url;
            frm.submit();
        }
    }

    function refreshKelompok() {
        var frm = document.forms.utilitiKelompok;
        var url = '${pageContext.request.contextPath}/pelupusan/permohonan_berkelompok?findKelompok';
        frm.action = url;
        frm.submit();
    }

    function tambahPermohonan() {
        //        doBlockUI();
        var idKelompok = $('#idKelompok').val();
        window.open("${pageContext.request.contextPath}/pelupusan/permohonan_berkelompok?tambahPermohonan&idKelompok=" + idKelompok, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=1000,height=500,scrollbars=yes");
    }

    function popup(url)
    {
        params = 'width=900';
        params += ', height=900';
        params += ', top=200, left=100';
        params += ', directories=no';
        params += ', fullscreen=no';
        params += ', location=no';
        params += ', menubar=yes';
        params += ', resizable=yes';
        params += ', scrollbars=yes';
        params += ', status=yes';
        params += ', toolbar=yes';
        newwin = window.open(url, 'PopUp', params);
        if (window.focus) {
            newwin.focus()
        }
        return false;
    }

    function refreshPermohonanKelompok(val) {
        //        alert(val);
        var frm = document.forms.utilitiKelompok;
        var url = '${pageContext.request.contextPath}/pelupusan/permohonan_berkelompok?editKelompok&idMohon=' + val;
        frm.action = url;
        frm.submit();
    }

    function selectCheckBox()
    {
        var e = $('#sizePermohonan').val();
        var idKelompok = $('#idKelompok').val();
        var cnt = 0;
        var data = new Array();
        for (cnt = 0; cnt < e; cnt++)
        {
            if (e == '1') {
                if (document.utilitiKelompok.checkmate.checked) {
                    //                     alert(document.frm.checkmate[cnt].value) ;
                    data[cnt] = document.utilitiKelompok.checkmate.value;
                }

            }
            else {
                if (document.utilitiKelompok.checkmate[cnt].checked) {
                    //                     alert(document.frm.checkmate[cnt].value) ;
                    data[cnt] = document.utilitiKelompok.checkmate[cnt].value;
                }
                else {
                    data[cnt] = "T";
                }
            }
        }
        //                    alert(data) ;
        if (confirm("Adakah anda pasti?")) {
            var url = '${pageContext.request.contextPath}/pelupusan/permohonan_berkelompok?deletePermohonan&item=' + data;
            $.get(url,
                    function(data) {
                        $('#page_div').html(data);
                        self.refreshPermohonanKelompok(idKelompok);
                    }, 'html');
        }
    }

    function janaPermohonan() {
        var idKelompok = $('#idKelompok').val();
        if (confirm("Adakah anda pasti untuk jana permohonan?")) {
            var url = '${pageContext.request.contextPath}/pelupusan/permohonan_berkelompok?janaPermohonan&idKelompok=' + idKelompok;
            $.get(url,
                    function(data) {
                        $('#page_div').html(data);
                        self.refreshPermohonanKelompok(idKelompok);
                    }, 'html');
        }
    }
//    ===============================================================================================================================
    function filterJabatan(f, kodJabatan) {
//               alert(kodJabatan);
        var queryString = $(f).formSerialize();
        var url = "${pageContext.request.contextPath}/Statusbayaran?filterByJabatan" + queryString + "&kodJabatan=" + kodJabatan;
        $.get(url,
                function(data) {
                    $('#jab').html(data);
                }, 'html');
    }
    function clearText1(id) {
        $("#" + id + " select").each(function(el) {
            $(this).val('0');
        });
    }

    function cetak(f, id1) {
        var queryString = $(f).formSerialize()
        window.open("${pageContext.request.contextPath}/hasil/semak_dokumen?cetak&" + queryString + "&kodUrusan=" + id1, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
    }


//    ===============================================================================================================================
</script>
<div id="jab">
    <stripes:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
    <stripes:errors />
    <stripes:form beanclass="etanah.view.stripes.pelupusan.utility.UtilitiStatusTanahActionBean" id="utilitiBayaran">
        <stripes:text name="kodNegeri" id="negeri" style="display:none;" />

        <fieldset class ="aras1">
            <legend>Carian Permohonan</legend>
            <p><label><font color="red">*</font>ID Mohon :</label>
                    <s:text id="idPermohonan" name="idPermohonan"></s:text>
                </p>
                <p align="center"><s:submit name="findStatusTanah" value="Cari" class="btn"/>&nbsp;
                <s:button name = "reset" class="btn" value ="Isi Semula" onclick= "clearText('Id Permohonan" /></p>

        </fieldset>
        <c:if test="${actionBean.flag}">
            <div class="content">
                <fieldset class="aras1">
                    <legend>
                        Status Laporan Tanah 
                    </legend>
                    <div class="content" align="center">
                        <display:table class="tablecloth" name="${actionBean}"
                                       id="line" style="width:95%"
                                       requestURI="${pageContext.request.contextPath}/pelupusan/utiliti/UtilitiStatusTanah">                    
                            <display:column title="Bil" class="bil${line_rowNum}" style="${bcolor}">      
                                ${line_rowNum}
                            </display:column>
                            <display:column title="ID Permohonan">
                                <c:if test="${line.idPermohonan ne null}">
                                    ${line.idPermohonan}
                                </c:if>
                            </display:column>
                            <display:column title="Id Hakmilik">                        
                                ${line.hakmilik}
                            </display:column>
                            <display:column title="Urusan">
                                ${line.urusan2}
                            </display:column>
                            <display:column title="Bandar/Pekan/Mukim">                        
                                ${line.kodBPM2}
                            </display:column>
                            <display:column title="Stage Id">                        
                                ${line.syorPen}
                            </display:column>
                        </display:table>
                    </div>
                </fieldset>
            </div>
        </c:if>
    </stripes:form>
</div>
