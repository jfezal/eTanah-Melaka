
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah-script.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/popup.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-impromptu.js"></script>
<style type="text/css">
    td.s{
        font-weight:normal;
        color:black;
        text-align: left;
    }
</style>

<script language="javascript">
    $(document).ready(function() {
        $('.alphanumeric').alphanumeric();
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});

        $('input').focus(function() {
            $(this).addClass("focus");
        });

        $('input').blur(function() {
            $(this).removeClass("focus");
        });

        $('select').focus(function() {
            $(this).addClass("focus");
        });

        $('select').blur(function() {
            $(this).removeClass("focus");
        });
    });


    function save(event, f, idH)
    {



        var q = $(f).formSerialize();
        var url = f.action + '?' + event + '&idH=' + idH;

        $.post(url, q,
                function(data) {
                    $('#page_div', opener.document).html(data);
                }, 'html');

        $.prompt('Maklumat Berjaya Disimpan.', {
            buttons: {Ok: true},
            prefix: 'jqismooth',
            submit: function(v, m, f) {
                self.close();
            }
        });

    }


    
      function deleteHakmilikAsal(val){
            var answer = confirm("adakah anda pasti untuk Hapus?");
            if (answer){
                var url = '${pageContext.request.contextPath}/daftar/pembetulan/betul?deleteHakmililAsal&idHakmilikAsal='+val;
                $.get(url,
                function(data){
                    $('#page_div').html(data);
                    self.close();
                });
            }
        }
        

</script>

<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<s:form name="form1" beanclass="etanah.view.stripes.nota.pembetulanActionBean">

    <s:messages />
    <s:errors />

    <div class="subtitle">



        <fieldset class="aras1">
            <legend>
                Maklumat Hakmilik Asal
            </legend>
            
             <div class="content" align="center">

                <table cellpadding="0" cellspacing="0" align="center" class="tablecloth">
                    <tr>
                        <th>ID Hakmilik Asal</th>
                        <th>ID Hakmilik</th>
                        <th>Hapus</th>
                    </tr>            
                    <tr>
                        <td>${actionBean.hakmilikAsal.hakmilikAsal}</td>
                        <td>${actionBean.hakmilik.idHakmilik}</td>
                        <td>
                            <div align="center">
                                <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                id='rem_${actionBean.hakmilikAsal.idAsal}' onclick="deleteHakmilikAsal('${actionBean.hakmilikAsal.idAsal}')" onmouseover="this.style.cursor = 'pointer';">
                            </div>
                        </td>
                    </tr>

                </table>
            </div>

            <table style="margin-left: auto; margin-right: auto;">
                <tr>
                    <td>&nbsp;</td>
                    <td><div >
                          <s:button name="tutup" value="Tutup" class="btn" onclick="javascript:self.close()"/>

                        </div>
                    </td>
                </tr>
            </table>
                        <p>
                            <label>&nbsp;</label>
        </p>
            </p>
            <br/>
        </fieldset>


    </div>

</s:form>